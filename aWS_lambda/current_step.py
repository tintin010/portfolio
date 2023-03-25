import sys
import logging 
import pymysql
import dbinfo
import json
from datetime import datetime


def is_json_key_present(json, key):
    try:
        buf = json[key]
    except KeyError:
        return False
    return True

#------------------------/ 메인 로직/------------------

def lambda_handler(event, context) :
    
    # -----------------------/ DB연결 /--------
    global conn
    
    conn = pymysql.connect(host=dbinfo.db_host, 
                            port=dbinfo.db_port, 
                            user=dbinfo.db_username, 
                            password=dbinfo.db_password, 
                            db=dbinfo.db_name)
    print("----- dbinfo -----")
    print(dbinfo)
    
    print("event: ", event)
    
    # 람다 단독실행하며 event에 keys 가 안 넘어온다.
    # 그래서, 그거 처리하려고 아래 로직을 담았다.
    blank = is_json_key_present(event, "requestContext")
    
    
    
    # blank = True
    # p_method = context
    
    if blank == True :
        p_method = event["requestContext"]["http"]["method"]
        print("--- method: ", p_method)
        if p_method == 'GET':
            print("--- method: GET")
            jsonData = user_get()
            
            return {
                'statusCode': 200,
                'body': json.dumps(jsonData)
            }

    else:
        print("Lambda self excute ")
        
# -----------------------------------------------------------------------------------------------------

def user_get():
# user 테이블 전체를 조회

    cursor = conn.cursor()
    cursor.execute("SELECT bcd.code_detail_id , bcd.code_detail_name FROM BCDD.bcd_code_detail bcd WHERE bcd.code_group_id = 'STEP';")

    rows = cursor.fetchall()
    jsonData = {}
    jsonData['data'] = []   # 배열로 담기위해

    cnt = 0
    ln = len(rows)
    print("----- ln: ", ln)
    
    for row in rows:
        cnt += 1
        print("--- ", cnt, " ---")
        print(row)
        jsonData['data'].append({
            "code_detail_id":      str(row[0]),
            "code_detail_name":    str(row[1])
        })
    print(jsonData)

    cursor.close()
    conn.close()
    
    return jsonData
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
        # print("--- method: ", p_method)

        if p_method == 'GET':
            print("--- method: GET")
            # body = event["queryStringParameters"]["body"]
            # body = event["pathParameters"]["id"]
            # print(body)
            jsonData = user_get()
            
            return {
                'statusCode': 200,
                'body': json.dumps(jsonData)
                # 'body': json.dumps(jsonData)
            }
        elif p_method == 'POST':  # 여기에 post 로직을 넣어 두고
            print("--- method: POST")
            body = event["queryStringParameters"]["body"]
            msg = user_post(body)
            return {
                'statusCode': 200,
                'body': msg
            } 
            
    #--------------------------------------------------------------------------------------------
    
def user_get():
    
    # data = json.loads(body.replace("'", "\""))
    # print(body)
# user 테이블 전체를 조회

    sql = "select a.yymm, a.user_id, a.project_name, a.activity_name, sum(duration_time) as d_sum, bcd.code_detail_name"
    sql = sql + " from (select timesheet_id, user_id, date, DATE_FORMAT(date,'%Y-%m') as yymm,"
    sql = sql + " duration_time, project_name, activity_name from BCDD.bcd_timesheet) a, BCDD.bcd_code_detail bcd"
    sql = sql + " WHERE bcd.code_detail_id = a.activity_name"
    sql = sql + " group by a.yymm, a.user_id, a.project_name, a.activity_name;"

    cursor = conn.cursor()
    cursor.execute(sql)

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
            "yymm":                     str(row[0]),
            "user_id":                  str(row[1]),
            "project_name":             str(row[2]),
            "activity_name":            str(row[3]),
            "d_sum":                    str(row[4]),
            "code_detail_name" :        str(row[5])
        })
        
    print(jsonData)
    # jsonData = json.dumps(jsonData)

    cursor.close()
    conn.close()    
    
    return jsonData
    
    # -------------------------------------------------------------------------------------------------------

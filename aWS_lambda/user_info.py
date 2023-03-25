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
            body = event["pathParameters"]["id"]
            # print(body)
            jsonData = user_get(body)
            
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
        elif p_method == 'PUT':  # 여기에 post 로직을 넣어 두고
            print("--- method: PUT")
            body = event["queryStringParameters"]["body"]
            msg = user_update(body)
            return {
                'statusCode': 200,
                'body': msg
            }
            
    #--------------------------------------------------------------------------------------------
    
def user_get(body):
    
    # data = json.loads(body.replace("'", "\""))
    print("body : "+body)
    # user 테이블 전체를 조회
    sql = "SELECT user_role from BCDD.bcd_user bu WHERE bu.user_id = " + body

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
            "user_role":                  str(row[0])
        })
        
    print(jsonData)
    # jsonData = json.dumps(jsonData)

    cursor.close()
    conn.close()    
    
    return jsonData
    
    
    
# -----------------------------------------------------------------------------------------------------


def user_update(body):
    # data에 user 관련 항목이 담겨온다
    
    data = json.loads(body.replace("'", "\""))   # string_to_dict 
    
    print("body: ", '-'*20)
    print(body)
    print("data: ", '-'*20)
    print(data)
    print('-'*20)


    sql = "UPDATE BCDD.bcd_user bu set bu.password = '"+ data['password'] +"' WHERE bu.hand_phone_no  = '"+data['user_id']+"'"

    cursor = conn.cursor()
    cnt = cursor.execute(sql)

    conn.commit()

    cursor.close()
    conn.close()

    return "Update OK"
    # -------------------------------------------------------------------------------------------------------
    
    
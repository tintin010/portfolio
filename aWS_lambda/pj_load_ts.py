import sys
import logging 
import pymysql
import dbinfo
import json
from datetime import datetime


# db      = pymysql.connect(host="13.209.48.163", port=3306, user="namu", password="namu123!", db="BCDD")
# cursor  = db.cursor(pymysql.cursors.DictCursor)
# print(db)
# print(cursor)



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
            body = event["queryStringParameters"]["body"]
            jsonData = user_get(body)
            
            return {
                'statusCode': 200,
                'body': json.dumps(jsonData)
            }
        elif p_method == 'POST':  # 여기에 post 로직을 넣어 두고
            print("--- method: POST")
            body = event["queryStringParameters"]["body"]
            msg = user_post(body)
            return {
                'statusCode': 200,
                'body': msg
            }            

        elif p_method == 'PUT':
            print("method: PUT")
            body = event["queryStringParameters"]["body"]
            msg = user_update(body)
            return {
                'statusCode': 200,
                'body': msg
            }            
            
            
        elif p_method == 'DELETE':  # 여기에 삭제 로직을 넣어 두고
            print("--- method: DELETE")
            body = event["queryStringParameters"]["body"]
            msg = user_delete(body)
            return {
                'statusCode': 200,
                'body': msg
            }
    else:
        print("Lambda self excute ")
        
# -----------------------------------------------------------------------------------------------------

def user_get(body):
# user 테이블 전체를 조회
    data = json.loads(body.replace("'", "\""))   # string_to_dict 

    sql = "SELECT bt.timesheet_id , bt.user_id , bt.date, bt.st_time , bt.end_time , bt.duration_time , bt.project_name , bt.memo ,bt.activity_name"
    sql = sql + " from BCDD.bcd_timesheet bt  WHERE bt.timesheet_id = "+data['timesheet_id']+" ;" 
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
            "timesheet_id":         str(row[0]),
            "user_id":              str(row[1]),
            "date":                 str(row[2]),
            "st_time":              str(row[3]),
            "end_time":             str(row[4]),
            "duration_time":        str(row[5]),
            "project_name":         str(row[6]),
            "memo":                 str(row[7]),
            "activity":             str(row[8])
        })
        
    print(jsonData)

    cursor.close()
    conn.close()
    
    return jsonData
        
# -----------------------------------------------------------------------------------------------------
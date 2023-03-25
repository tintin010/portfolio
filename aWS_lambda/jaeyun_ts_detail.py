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
            body = event["queryStringParameters"]["body"]
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
            
    #--------------------------------------------------------------------------------------------
    
def user_get(body):
    
    data = json.loads(body.replace("'", "\""))
    print("body : "+body)
    print("data : ", data)
    # user 테이블 전체를 조회
    sql = "SELECT bt.`date` , bt.project_name , bt.activity_name , bt.st_time , bt.end_time , bt.duration_time , bt.memo , bu.name, bcd.code_detail_name FROM BCDD.bcd_timesheet bt, BCDD.bcd_user bu, BCDD.bcd_code_detail bcd "
    sql = sql + "WHERE bu.user_id = "+data['user_id']+" and bt.user_id = '"+data['user_id']+"' and bt.project_name = '"+data['ts_project_name']+"' and bt.`date` >last_day('"+data['now_month']+"' - interval 1 month) and bt.`date` <= last_day('"+data['now_month']+"') and bcd.code_detail_id = bt.activity_name;"

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
            "date":             str(row[0]),
            "project_name":     str(row[1]),
            "activity_id":    str(row[2]),
            "st_time":          str(row[3]),
            "end_time":         str(row[4]),
            "duration_time":    str(row[5]),
            "memo":             str(row[6]),
            "name":             str(row[7]),
            "activity_name":    str(row[8]),
        })          
        
    print(jsonData)
    # jsonData = json.dumps(jsonData)

    cursor.close()
    conn.close()    
    
    return jsonData
    
    # -------------------------------------------------------------------------------------------------------

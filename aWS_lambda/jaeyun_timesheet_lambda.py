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
        
        # ----------------------------------------------------------------------------------------

def user_post(body):
    # data에 user 관련 항목이 담겨온다
    
    data = json.loads(body.replace("'", "\""))   # string_to_dict 
    
    print("body: ", '-'*20)
    print(body)
    print("data: ", '-'*20)
    print(data)
    print('-'*20)

    fields = ['user_id', 'date', 'st_time', 'end_time', 'duration_time', 'project_name', 'activity_name', 'memo']
    print("----- field_list count : ", len(fields))

    valueStr = "( '"
    cnt = 0

    for field in fields:      # 글자 합치기
        cnt += 1
        if cnt == 1 :
            valueStr = valueStr  + data[field]
        else:
            valueStr = valueStr  + "', '" + data[field]
    
    valueStr = valueStr  + "')"

    print('--- valueStr : ', valueStr)

    print("user_post(data)", data)

    sql = "INSERT INTO BCDD.bcd_timesheet " 
    sql = sql + "(user_id, date, st_time, end_time, duration_time, project_name, activity_name, memo) "
    sql = sql + " VALUES "
    sql = sql + valueStr
    # sql = sql + "('김새롬', '010-9999-5678', 'dddd@naver.com', '부시똘', 'M', '2022-08-02', '2022-08-01', '2022-08-13 14:45:45');"

    cursor = conn.cursor()
    cnt = cursor.execute(sql)

    print("insert cnt : ", cnt)
    conn.commit()

    cursor.close()
    conn.close()

    return "Post OK"

# -----------------------------------------------------------------------------------------------------

def user_get():
# user 테이블 전체를 조회


    sql = "select bt.timesheet_id , bt.user_id , bt.date , bt.st_time , bt.end_time , bt.duration_time, bt.project_name , bcd.code_detail_name, bt.activity_name, bt.memo from BCDD.bcd_timesheet bt , BCDD.bcd_code_detail bcd WHERE bcd.code_detail_id = bt.activity_name;"
    
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
            "timesheet_id":     str(row[0]),
            "user_id":          str(row[1]),
            "date":             str(row[2]),
            "st_time":          str(row[3]),
            "end_time":         str(row[4]),
            "duration_time":    str(row[5]),
            "project_name":     str(row[6]),
            "code_detail_name": str(row[7]),
            "activity_name":    str(row[8]),
            "memo":             str(row[9])
        })
        
    print(jsonData)

    cursor.close()
    conn.close()
    
    return jsonData
        
# -----------------------------------------------------------------------------------------------------
        
def user_delete(body):
    # data에 user 관련 항목이 담겨온다
    
    data = json.loads(body.replace("'", "\""))   # string_to_dict 
    
    print("body: ", '-'*20)
    print(body)
    print("data: ", '-'*20)
    print(data)
    print('-'*20)


    print("user_post(data)", data)

    sql = "DELETE FROM BCDD.bcd_timesheet WHERE timesheet_id = " + data['timesheet_id'] + ";"

    cursor = conn.cursor()
    cnt = cursor.execute(sql)

    print("Delete cnt : ", cnt)
    conn.commit()

    cursor.close()
    conn.close()

    return "Delete OK"

        
        
# -----------------------------------------------------------------------------------------------------


def user_update(body):
    # data에 user 관련 항목이 담겨온다
    
    data = json.loads(body.replace("'", "\""))   # string_to_dict 
    
    print("body: ", '-'*20)
    print(body)
    print("data: ", '-'*20)
    print(data)
    print('-'*20)


    sql = "UPDATE BCDD.bcd_timesheet SET user_id='"+ data['user_id'] +"', `date`='"+ data['date']
    sql = sql + "', st_time='"+ data['st_time'] +"', end_time='"+ data['end_time'] +"', duration_time="+ data['duration_time']
    sql = sql + ", project_name='"+ data['project_name'] +"', activity_name='"+ data['activity_name'] +"', memo='"+ data['memo']
    sql = sql + "' WHERE timesheet_id="+ data['timesheet_id'] +";"
    # sql = sql + "('김새롬', '010-9999-5678', 'dddd@naver.com', '부시똘', 'M', '2022-08-02', '2022-08-01', '2022-08-13 14:45:45');"

    cursor = conn.cursor()
    cnt = cursor.execute(sql)

    conn.commit()

    cursor.close()
    conn.close()

    return "Update OK"







# -----------------------------------------------------------------------------------------------------

    print(jsonData)

    cursor.close()
    conn.close()
    
    return jsonData
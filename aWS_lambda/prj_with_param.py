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
            print(body)
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
            
    #--------------------------------------------------------------------------------------------
def user_get(body):
    
    # data = json.loads(body.replace("'", "\""))
    print(body)
# user 테이블 전체를 조회
    cursor = conn.cursor()
    cursor.execute("select * from BCDD.bcd_prj_master where project_id = " + body)

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
            "project_id":               str(row[0]),
            "project_name":             str(row[1]),
            "contractor":               str(row[2]),
            "addr":                     str(row[3]),
            "contact_tel":              str(row[4]),
            "email":                    str(row[5]),
            "comm_history":             str(row[6]),
            "PBT_send_date":            str(row[7]),
            "contract_amount":          str(row[8]),
            "ordering_body":            str(row[9]),
            "contract_day":             str(row[10]),
            "current_status":           str(row[11])
        })
        
    print(jsonData)
    # jsonData = json.dumps(jsonData)

    cursor.close()
    conn.close()    
    
    return jsonData
    
    # -------------------------------------------------------------------------------------------------------

def user_post(body):
    # data에 user 관련 항목이 담겨온다
    
    data = json.loads(body.replace("'", "\""))   # string_to_dict 
    
    print("body: ", '-'*20)
    print(body)
    print("data: ", '-'*20)
    print(data)
    print('-'*20)

    fields = ['project_id', 'project_name', 'contractor', 'addr', 'contact_tel', 'email', 'comm_history', 'PBT_send_date', 'contract_amount','ordering_body','contract_day','current_status']
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

    sql = "INSERT INTO BCDD.bcd_prj_master "
    sql = sql + "(project_id, project_name, contractor, addr, contact_tel, email, comm_history, PBT_send_date, contract_amount, ordering_body, contract_day, current_status)"
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
    
    # -------------------------------------------------------------------------------------------------------
    
def user_delete(body):
    # data에 user 관련 항목이 담겨온다
    
    data = json.loads(body.replace("'", "\""))   # string_to_dict 
    
    print("body: ", '-'*20)
    print(body)
    print("data: ", '-'*20)
    print(data)
    print('-'*20)


    print("user_post(data)", data)

    sql = "DELETE FROM BCDD.bcd_prj_master WHERE project_id = " + data['project_id'] + ";"

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


    sql = "UPDATE BCDD.bcd_prj_master SET project_id='"+ data['project_id'] +"', `project_name`='"+ data['project_name']
    sql = sql + "', contractor='"+ data['contractor'] +"', addr='"+ data['addr'] +"', contact_tel="+ data['contact_tel']
    sql = sql + ", email='"+ data['email'] +"', comm_history='"+ data['comm_history'] +"', PBT_send_date='"+ data['PBT_send_date']
    sql = sql + ", contract_amount='"+ data['contract_amount'] +"', ordering_body='"+ data['ordering_body']
    sql = sql + "', contract_day='"+ data['contract_day']+"', current_status='"+ data['current_status']
    sql = sql + "' WHERE project_id="+ data['project_id'] +";"
    # sql = sql + "('김새롬', '010-9999-5678', 'dddd@naver.com', '부시똘', 'M', '2022-08-02', '2022-08-01', '2022-08-13 14:45:45');"

    cursor = conn.cursor()
    cnt = cursor.execute(sql)

    conn.commit()

    cursor.close()
    conn.close()

    return "Update OK"
    
    
    # -------------------------------------------------------------------------------------------------------
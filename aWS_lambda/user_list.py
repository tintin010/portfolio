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

        # if p_method == 'GET':
        #     print("--- method: GET")
        #     body = event["pathParameters"]["id"]
        #     jsonData = user_get(body)
            
        #     return {
        #         'statusCode': 200,
        #         'body': json.dumps(jsonData)
        #     }
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
        
# -----------------------------------------------------------------------------------------------------

def user_get():
# user 테이블 전체를 조회

    
    sql = "select a.user_id, a.name, a.hand_phone_no, a.email, a.comp, a.user_role, a.password, CONCAT_WS(',',a.rn1,a.rn2,a.rn3) as r_name,a.entry_date, a.reg_date, a.last_conn_date,a.user_status, (@row_number:=@row_number + 1) "
    sql = sql + " from ( SELECT user_id, name, hand_phone_no, email, user_role,comp, password, SUBSTR(user_role, 1, 1) as f1,"
    sql = sql + " (select  code_detail_name from BCDD.bcd_code_detail where code_group_id = 'ROLE' and code_detail_id = SUBSTR(user_role, 1, 1)) as rn1,"
    sql = sql + " SUBSTR(user_role,3,1) as f2, (select  code_detail_name from BCDD.bcd_code_detail where code_group_id = 'ROLE' and code_detail_id = SUBSTR(user_role, 3, 1)) as rn2, SUBSTR(user_role,5,1) as f3,"
    sql = sql + " (select  code_detail_name from BCDD.bcd_code_detail where code_group_id = 'ROLE' and code_detail_id = SUBSTR(user_role, 5, 1)) as rn3,"
    sql = sql + " entry_date, reg_date, last_conn_date, user_status FROM bcd_user where is_use = 'Y') a, (SELECT @row_number:=0) t ;"
    
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
            "user_id":          str(row[0]),
            "name":             str(row[1]),
            "hand_phone_no":    str(row[2]),
            "email":            str(row[3]),
            "comp":             str(row[4]),
            "user_role":        str(row[5]),
            "password":         str(row[6]),
            "r_name":           str(row[7]),
            "entry_date":       str(row[8]),
            "reg_date":         str(row[9]),
            "last_conn_date":   str(row[10]),
            "user_status":      str(row[11]),
            "num":              str(row[12])
            
        })
        
    print(jsonData)

    cursor.close()
    conn.close()
    
    return jsonData
        
# -----------------------------------------------------------------------------------------------------

def user_post(body):
    # data에 user 관련 항목이 담겨온다
    
    data = json.loads(body.replace("'", "\""))   # string_to_dict 
    
    print("body: ", '-'*20)
    print(body)
    print("data: ", '-'*20)
    print(data)
    print('-'*20)

    fields = ['name', 'hand_phone_no', 'email', 'comp', 'user_role', 'entry_date', 'reg_date']
    print("----- field_list count : ", len(fields))
    
    data['user_role'] = ",".join(list(data['user_role']))

    valueStr = "( '"
    cnt = 0

    for field in fields:      # 글자 합치기
        cnt += 1
        if cnt == 1 :
            valueStr = valueStr  + data[field]
        else:
            valueStr = valueStr  + "', '" + data[field]
        
    valueStr = valueStr + "', '재직중"
    
    valueStr = valueStr  + "')"

    print('--- valueStr : ', valueStr)

    print("user_post(data)", data)

    sql = "INSERT INTO BCDD.bcd_user " 
    sql = sql + "(name, hand_phone_no, email, comp, user_role, entry_date, reg_date, user_status)"
    sql = sql + " VALUES "
    sql = sql + valueStr 

    cursor = conn.cursor()
    cnt = cursor.execute(sql)

    print("insert cnt : ", cnt)
    conn.commit()

    cursor.close()
    conn.close()

    return "Post OK"

# -----------------------------------------------------------------------------------------------------

# -----------------------------------------------------------------------------------------------------


def user_update(body):
    # data에 user 관련 항목이 담겨온다
    
    data = json.loads(body.replace("'", "\""))   # string_to_dict 
    
    print("body: ", '-'*20)
    print(body)
    print("data: ", '-'*20)
    print(data)
    print('-'*20)


    sql = "UPDATE BCDD.bcd_deposit SET project_id='"+ data['project_id'] +"', deposit_phase='"+ data['deposit_phase']
    sql = sql + "', plan_deposit_date='"+ data['plan_deposit_date'] +"', plan_deposit_amount='"+ data['plan_deposit_amount'] +"', bill_issue_date="+ data['bill_issue_date']
    sql = sql + ", bill_issue_amount='"+ data['bill_issue_amount'] +"', actual_deposit_date='"+ data['actual_deposit_date'] +"', actual_deposit_amount='"+ data['actual_deposit_amount'] +"', memo='"+ data['memo']
    sql = sql + "' WHERE project_id="+ data['project_id'] +" AND deposit_phase="+data['deposit_phase']+";"
    # sql = sql + "('김새롬', '010-9999-5678', 'dddd@naver.com', '부시똘', 'M', '2022-08-02', '2022-08-01', '2022-08-13 14:45:45');"

    cursor = conn.cursor()
    cnt = cursor.execute(sql)

    conn.commit()

    cursor.close()
    conn.close()

    return "Update OK"
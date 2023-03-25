
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
    
    
    
    blank = True
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

    else:
        print("Lambda self excute ")
    
    

def user_post(body):
    # data에 user 관련 항목이 담겨온다
    
    # data1 = "{'name': '이효리', 'hand_phone_no': '010-4545-5656','email': 'lee@naver.com', 'comp': '부시똘', 'user_role': 'C', 'entry_date': '2022-04-04', 'reg_date': '2022-06-15', 'last_conn_date': '2022-06-22 14:45:45'}"
    data = json.loads(body.replace("'", "\""))   # string_to_dict 
    
    print("body: ", '-'*20)
    print(body)
    print("data: ", '-'*20)
    print(data)
    print('-'*20)

    fields = ['name', 'hand_phone_no', 'email', 'comp', 'user_role', 'entry_date', 'reg_date', 'last_conn_date']
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

    sql = "INSERT INTO BCDD.bcd_user " 
    sql = sql + "(name, hand_phone_no, email, comp, user_role, entry_date, reg_date, last_conn_date) "
    sql = sql + " VALUES "
    sql = sql + valueStr
    # sql = sql + "('김새롬', '010-9999-5678', 'dddd@naver.com', '부시똘', 'M', '2022-08-02', '2022-08-01', '2022-08-13 14:45:45');"

    cursor = conn.cursor()
    cnt = cursor.execute(sql)

    print("insert cnt : ", cnt)
    conn.commit()

    cursor.close()
    conn.close()

    return "Insert OK"



def user_get():
# user 테이블 전체를 조회

    cursor = conn.cursor()
    cursor.execute("select * from BCDD.bcd_user")   

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
        # print(row[0],row[1],row[2],row[3],row[4],row[5])
        
        # print("{0} {1} {2}".format(row[0],row[1],row[2]))
        # dd = "{ id:"&row[0]&", amount:"&row[1]&", tr_type:"&row[2] & " }"
        jsonData['data'].append({
            "user_id":          str(row[0]),
            "name":             str(row[1]),
            "hand_phone_no":    str(row[2]),
            "email":            str(row[3]),
            "comp":             str(row[4]),
            "user_role":        str(row[5]),
            "entry_date":       str(row[6]),
            "reg_date":         str(row[7]),
            "last_conn_date":   str(row[8]),
            "is_use":           str(row[9]),
            "user_status":      str(row[10]),
            "password":         str(row[11])
        })

    print(jsonData)

    cursor.close()
    conn.close()
    
    return jsonData

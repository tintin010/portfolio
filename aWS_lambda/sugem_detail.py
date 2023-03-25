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

    sql = "with  step_name as(select code_detail_id, code_detail_name from BCDD.bcd_code_detail where code_group_id = 'PHASE')"
    sql = sql + "SELECT bd.deposit_phase , bpm.contract_day, bd.plan_deposit_date , bd.bill_issue_date , bd.plan_deposit_amount, bd.bill_issue_amount , bd.actual_deposit_date , bd.actual_deposit_amount , bd.memo,concat((select code_detail_name from step_name where code_detail_id = b.ph), b.step) as strr "
    sql = sql + "FROM BCDD.bcd_deposit bd, BCDD.bcd_prj_master bpm, (select a.p_id, a.ph,case when  a.act = a.pln THEN concat(' 입금', DATE_FORMAT(a.adt, '(%m/%d)')) " 
    sql = sql + "when  a.act < a.pln AND TIMESTAMPDIFF(day, a.pdt , NOW()) <=14 THEN concat(' 모니터링, ', TIMESTAMPDIFF(day, a.pdt, NOW()), '일') "
    sql = sql + "when  a.act < a.pln AND TIMESTAMPDIFF(day, a.pdt , NOW()) <=30 THEN concat(' 미수1단계, ', TIMESTAMPDIFF(day, a.pdt, NOW()), '일') "
    sql = sql + "when  a.act < a.pln AND TIMESTAMPDIFF(day, a.pdt , NOW()) <=60 THEN concat(' 미수2단계, ', TIMESTAMPDIFF(day, a.pdt, NOW()), '일') "
    sql = sql + "when  a.act < a.pln AND TIMESTAMPDIFF(day, a.pdt , NOW()) >=61 THEN concat(' 불량, ', TIMESTAMPDIFF(day, a.pdt, NOW()), '일') "
    sql = sql + "else  a.ph end as step from (select project_id as p_id, actual_deposit_amount as act, plan_deposit_amount as pln, plan_deposit_date as pdt, deposit_phase as ph, actual_deposit_date as adt "
    sql = sql + "from bcd_deposit bd) as a) as b WHERE bd.project_id = '" + data['project_id'] + "' AND bpm.project_id = '" + data['project_id'] + "' AND b.ph = bd.deposit_phase AND b.p_id = '" + data['project_id'] + "';"

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
            "deposit_phase":            str(row[0]),
            "contract_day":             str(row[1]),
            "plan_deposit_date":        str(row[2]),
            "bill_issue_date":          str(row[3]),
            "plan_deposit_amount":      str(row[4]),
            "bill_issue_amount":        str(row[5]),
            "actual_deposit_date":      str(row[6]),
            "actual_deposit_amount":    str(row[7]),
            "memo":                     str(row[8]),
            "status":                    str(row[9])
            
            
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

    fields = ['project_id', 'deposit_phase', 'plan_deposit_date', 'plan_deposit_amount', 'bill_issue_date', 'bill_issue_amount', 'actual_deposit_date', 'actual_deposit_amount', 'memo']
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

    sql = "INSERT INTO BCDD.bcd_deposit " 
    sql = sql + "(project_id, deposit_phase, plan_deposit_date, plan_deposit_amount, bill_issue_date, bill_issue_amount, actual_deposit_date, actual_deposit_amount, memo) "
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

def user_update(body):
    # data에 user 관련 항목이 담겨온다
    
    data = json.loads(body.replace("'", "\""))   # string_to_dict
    
    print("body: ", '-'*20)
    print(body)
    print("data: ", '-'*20)
    print(data)
    print('-'*20)


    sql = "UPDATE BCDD.bcd_deposit SET project_id='"+ data['project_id'] +"', deposit_phase='"+ data['deposit_phase']
    sql = sql + "', plan_deposit_date='"+ data['plan_deposit_date'] +"', plan_deposit_amount='"+ data['plan_deposit_amount'] +"', bill_issue_date='"+ data['bill_issue_date']
    sql = sql + "', bill_issue_amount='"+ data['bill_issue_amount'] +"', actual_deposit_date='"+ data['actual_deposit_date'] +"', actual_deposit_amount='"+ data['actual_deposit_amount'] +"', memo='"+ data['memo']
    sql = sql + "' WHERE project_id="+ data['project_id'] +" AND deposit_phase="+data['deposit_phase']+";"
    # sql = sql + "('김새롬', '010-9999-5678', 'dddd@naver.com', '부시똘', 'M', '2022-08-02', '2022-08-01', '2022-08-13 14:45:45');"

    cursor = conn.cursor()
    cnt = cursor.execute(sql)

    conn.commit()

    cursor.close()
    conn.close()

    return "Update OK"
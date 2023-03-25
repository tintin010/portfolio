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
            
    #--------------------------------------------------------------------------------------------
            
def user_get(body):
# user 테이블 전체를 조회

    data = json.loads(body.replace("'", "\""))   # string_to_dict
    sql = "select pm.project_id, pm.project_name, pm.current_status, pm.contract_day, pm.contractor, pm.addr, pm.contact_tel, pm.email, pm.ordering_body, a.end_date from BCDD.bcd_prj_master pm"
    sql = sql + " left outer join (select bcm.project_id, bcm.end_date, bcm.const_degree from BCDD.bcd_const_mngt bcm where bcm.const_degree = 4)as a"
    sql = sql + " ON pm.project_id = a.project_id WHERE pm.project_name like '%"+ data['search_var'] +"%';"
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
            "project_id":               str(row[0]),
            "project_name":             str(row[1]),
            "current_status":           str(row[2]),
            "contract_day":             str(row[3]),
            "contractor":               str(row[4]),
            "addr":                     str(row[5]),
            "contact_tel":              str(row[6]),
            "email":                    str(row[7]),
            "ordering_body":            str(row[8]),
            "end_date":                 str(row[9])
        })
        
    print(jsonData)

    cursor.close()
    conn.close()
    
    return jsonData
    
    
    
        # sql = "with step_name as(select code_detail_id, code_detail_name from BCDD.bcd_code_detail where code_group_id = 'PHASE')select pm.project_id, pm.project_name, pm.current_status, pm.contract_day, pm.contractor, "
    # sql = sql + "pm.addr, pm.contact_tel, pm.email, pm.ordering_body, (@row_number:=@row_number + 1) as 'NO.', c.strr from BCDD.bcd_prj_master pm "
    # sql = sql + "left outer join(select GROUP_CONCAT(concat((select code_detail_name from step_name where code_detail_id = b.ph),b.step) SEPARATOR '\n') as strr, b.p_id "
    # sql = sql + "FROM (select a.p_id, a.ph,case when  a.act = a.pln THEN concat(' 입금', DATE_FORMAT(a.adt, '(%m/%d)'))when  a.act < a.pln AND TIMESTAMPDIFF(day, a.pdt , NOW()) <=14 THEN concat(' 모니터링, ', TIMESTAMPDIFF(day, a.pdt, NOW()), '일') "
    # sql = sql + "when  a.act < a.pln AND TIMESTAMPDIFF(day, a.pdt , NOW()) <=30 THEN concat(' 미수1단계, ', TIMESTAMPDIFF(day, a.pdt, NOW()), '일')when  a.act < a.pln AND TIMESTAMPDIFF(day, a.pdt , NOW()) <=60 THEN concat(' 미수2단계, ', TIMESTAMPDIFF(day, a.pdt, NOW()), '일') "
    # sql = sql + "when  a.act < a.pln AND TIMESTAMPDIFF(day, a.pdt , NOW()) >=61 THEN concat(' 불량, ', TIMESTAMPDIFF(day, a.pdt, NOW()), '일')else  a.ph end as step from ( "
    # sql = sql + "select project_id as p_id, actual_deposit_amount as act, plan_deposit_amount as pln, plan_deposit_date as pdt, deposit_phase as ph, actual_deposit_date as adt from bcd_deposit bd) as a ) as b group by b.p_id "
    # sql = sql + ") as c ON pm.project_id = c.p_id, (SELECT @row_number:=0) t ;"
    
    
    # -------------------------------------------------------------------------------------------------------
def user_post(body):
    # data에 user 관련 항목이 담겨온다
    
    data = json.loads(body.replace("'", "\""))   # string_to_dict 
    
    print("body: ", '-'*20)
    print(body)
    print("data: ", '-'*20)
    print(data)
    print('-'*20)

    fields = ['project_id', 'project_name', 'current_status', 'contract_day', 'ordering_body', 'contractor', 'contract_amount', 'addr', 'contact_tel', 'email', 'PBT_send_date', 'comm_history']
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
    sql = sql + "(project_id, project_name, current_status, contract_day, ordering_body, contractor, contract_amount, addr, contact_tel, email, PBT_send_date, comm_history) "
    sql = sql + " VALUES "
    sql = sql + valueStr
    # sql = sql + "('김새롬', '010-9999-5678', 'dddd@naver.com', '부시똘', 'M', '2022-08-02', '2022-08-01', '2022-08-13 14:45:45');"

    cursor = conn.cursor()
    cnt = cursor.execute(sql)
    # conn.commit()

    print("insert cnt : ", cnt)
    # ----------------------------------------------------------------------------------------------------------------
    #1차 착공 기간 bcd_const_mngt
    
    fields = ['project_id', 'st_1','ed_1']
    print("----- field_list count : ", len(fields))

    valueStr = "( '"
    cnt = 0

    for field in fields:      # 글자 합치기
        cnt += 1
        if cnt == 1 :
            valueStr = valueStr  + data[field]
        else:
            valueStr = valueStr  + "', '" + data[field]
    
    valueStr = valueStr  + "', '" + "1" +"')"

    print('--- valueStr : ', valueStr)

    print("user_post(data)", data)

    sql = "INSERT INTO BCDD.bcd_const_mngt "
    sql = sql + "( project_id, st_date, end_date, const_degree) "
    sql = sql + " VALUES "
    sql = sql + valueStr

    cursor = conn.cursor()
    cnt = cursor.execute(sql)
    # conn.commit()
    
    # ----------------------------------------------------------------------------------------------------------------
    #2차 착공 기간 bcd_const_mngt
    fields = ['project_id', 'st_2','ed_2']
    print("----- field_list count : ", len(fields))

    valueStr = "( '"
    cnt = 0

    for field in fields:      # 글자 합치기
        cnt += 1
        if cnt == 1 :
            valueStr = valueStr  + data[field]
        else:
            valueStr = valueStr  + "', '" + data[field]
    
    valueStr = valueStr  + "', '" + "2" +"')"

    print('--- valueStr : ', valueStr)

    print("user_post(data)", data)

    sql = "INSERT INTO BCDD.bcd_const_mngt "
    sql = sql + "( project_id, st_date, end_date, const_degree) "
    sql = sql + " VALUES "
    sql = sql + valueStr

    cursor = conn.cursor()
    cnt = cursor.execute(sql)
    # conn.commit()
    
    # ----------------------------------------------------------------------------------------------------------------
    #3차 착공 기간 bcd_const_mngt
    fields = ['project_id', 'st_3','ed_3']
    print("----- field_list count : ", len(fields))

    valueStr = "( '"
    cnt = 0

    for field in fields:      # 글자 합치기
        cnt += 1
        if cnt == 1 :
            valueStr = valueStr  + data[field]
        else:
            valueStr = valueStr  + "', '" + data[field]
    
    valueStr = valueStr  + "', '" + "3" +"')"

    print('--- valueStr : ', valueStr)

    print("user_post(data)", data)

    sql = "INSERT INTO BCDD.bcd_const_mngt "
    sql = sql + "( project_id, st_date, end_date, const_degree) "
    sql = sql + " VALUES "
    sql = sql + valueStr

    cursor = conn.cursor()
    cnt = cursor.execute(sql)
    # conn.commit()
    
    # ----------------------------------------------------------------------------------------------------------------
    #4차 착공 기간 bcd_const_mngt
    fields = ['project_id', 'st_4','ed_4']
    print("----- field_list count : ", len(fields))

    valueStr = "( '"
    cnt = 0

    for field in fields:      # 글자 합치기
        cnt += 1
        if cnt == 1 :
            valueStr = valueStr  + data[field]
        else:
            valueStr = valueStr  + "', '" + data[field]
    
    valueStr = valueStr  + "', '" + "4" +"')"

    print('--- valueStr : ', valueStr)

    print("user_post(data)", data)

    sql = "INSERT INTO BCDD.bcd_const_mngt "
    sql = sql + "( project_id, st_date, end_date, const_degree) "
    sql = sql + " VALUES "
    sql = sql + valueStr

    cursor = conn.cursor()
    cnt = cursor.execute(sql)
    
    # ----------------------------------------------------------------------------------------------------------------
    
    
    conn.commit()

    cursor.close()
    conn.close()

    return "Post OK"
    
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

    sql = "DELETE FROM BCDD.bcd_prj_master WHERE project_id = " + data['project_id'] + ";"
    cursor = conn.cursor()
    cnt = cursor.execute(sql)
    
    sql = "DELETE FROM BCDD.bcd_const_mngt WHERE project_id = " + data['project_id'] + ";"
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


    sql = "UPDATE BCDD.bcd_prj_master SET project_id='"+ data['project_id'] +"', project_name='"+ data['project_name']
    sql = sql + "', current_status='"+ data['current_status'] +"', contract_day='"+ data['contract_day'] +"', ordering_body='"+ data['ordering_body']
    sql = sql + "', contractor='"+ data['contractor'] +"', contract_amount='"+ data['contract_amount'] +"', addr='"+ data['addr']
    sql = sql + "', contact_tel='"+ data['contact_tel'] +"', email='"+ data['email'] +"', PBT_send_date='"+ data['PBT_send_date'] + "', comm_history='"+data['comm_history']
    sql = sql + "' WHERE project_id="+ data['project_id'] +";"
    cursor = conn.cursor()
    cursor.execute(sql)
    
    sql = "UPDATE BCDD.bcd_const_mngt SET project_id='"+ data['project_id'] +"', const_degree='1"
    sql = sql + "', st_date='"+ data['st_1'] +"', end_date='"+ data['ed_1']
    sql = sql + "' WHERE project_id="+ data['project_id'] +" AND const_degree = '1';"
    cursor = conn.cursor()
    cursor.execute(sql)
    
    sql = "UPDATE BCDD.bcd_const_mngt SET project_id='"+ data['project_id'] +"', const_degree='2"
    sql = sql + "', st_date='"+ data['st_2'] +"', end_date='"+ data['ed_2']
    sql = sql + "' WHERE project_id="+ data['project_id'] +" AND const_degree = '2';"
    cursor = conn.cursor()
    cursor.execute(sql)

    sql = "UPDATE BCDD.bcd_const_mngt SET project_id='"+ data['project_id'] +"', const_degree='3"
    sql = sql + "', st_date='"+ data['st_3'] +"', end_date='"+ data['ed_3']
    sql = sql + "' WHERE project_id="+ data['project_id'] +" AND const_degree = '3';"
    cursor = conn.cursor()
    cursor.execute(sql)

    sql = "UPDATE BCDD.bcd_const_mngt SET project_id='"+ data['project_id'] +"', const_degree='4"
    sql = sql + "', st_date='"+ data['st_4'] +"', end_date='"+ data['ed_4']
    sql = sql + "' WHERE project_id="+ data['project_id'] +" AND const_degree = '4';"
    cursor = conn.cursor()
    cursor.execute(sql)

    conn.commit()

    cursor.close()
    conn.close()

    return "Update OK"
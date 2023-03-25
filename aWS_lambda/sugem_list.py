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
def user_get():
# user 테이블 전체를 조회


    sql = "SELECT bpm.project_id, bpm.project_name , bpm.contract_amount, a.phase1, a.phase2, a.phase3, a.phase4, a.phase5, "
    sql = sql + "(SELECT GROUP_CONCAT(a.step SEPARATOR '\n') FROM (SELECT CASE "
    sql = sql + "when bd.deposit_phase = '1' AND bd.actual_deposit_amount > 0 THEN concat('선급금 입금', DATE_FORMAT(bd.actual_deposit_date, '(%m/%d)')) when bd.deposit_phase = '1' AND bd.actual_deposit_amount = 0 THEN ('선급금 미수') "
    sql = sql + "when bd.deposit_phase = '2' AND bd.actual_deposit_amount > 0 THEN concat('1차 기성금 입금', DATE_FORMAT(bd.actual_deposit_date, '(%m/%d)')) when bd.deposit_phase = '2' AND bd.actual_deposit_amount = 0 THEN ('1차 기성금 미수') "
    sql = sql + "when bd.deposit_phase = '3' AND bd.actual_deposit_amount > 0 THEN concat('2차 기성금 입금', DATE_FORMAT(bd.actual_deposit_date, '(%m/%d)')) when bd.deposit_phase = '3' AND bd.actual_deposit_amount = 0 THEN ('2차 기성금 미수') "
    sql = sql + "when bd.deposit_phase = '4' AND bd.actual_deposit_amount > 0 THEN concat('3차 기성금 입금', DATE_FORMAT(bd.actual_deposit_date, '(%m/%d)')) when bd.deposit_phase = '4' AND bd.actual_deposit_amount = 0 THEN ('3차 기성금 미수') "
    sql = sql + "when bd.deposit_phase = '5' AND bd.actual_deposit_amount > 0 THEN concat('준공금 입금', DATE_FORMAT(bd.actual_deposit_date, '(%m/%d)')) when bd.deposit_phase = '5' AND bd.actual_deposit_amount = 0 THEN ('준공금 미수') "
    sql = sql + "else bd.deposit_phase END as step FROM BCDD.bcd_deposit bd WHERE  bd.project_id = bpm.project_id ) a) strr, (@row_number:=@row_number + 1) as num "
    sql = sql + "FROM BCDD.bcd_prj_master bpm "
    sql = sql + "left outer join "
    sql = sql + "(select bd.project_id, "
    sql = sql + "sum(case when bd.deposit_phase = '1' THEN bd.actual_deposit_amount END) phase1, "
    sql = sql + "sum(case when bd.deposit_phase = '2' THEN bd.actual_deposit_amount END) phase2, "
    sql = sql + "sum(case when bd.deposit_phase = '3' THEN bd.actual_deposit_amount END) phase3, "
    sql = sql + "sum(case when bd.deposit_phase = '4' THEN bd.actual_deposit_amount END) phase4, "
    sql = sql + "sum(case when bd.deposit_phase = '5' THEN bd.actual_deposit_amount END) phase5 "
    sql = sql + "from BCDD.bcd_deposit bd "
    sql = sql + "group by project_id "
    sql = sql + ") as a "
    sql = sql + "ON bpm.project_id = a.project_id, (SELECT @row_number:=0) t;"

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
            "contract_amount":          str(row[2]),
            "phase1":                   str(row[3]),
            "phase2":                   str(row[4]),
            "phase3":                   str(row[5]),
            "phase4":                   str(row[6]),
            "phase5":                   str(row[7]),
            "status":                   str(row[8]),
            "num":                      str(row[9])
        })
        
    print(jsonData)

    cursor.close()
    conn.close()
    
    return jsonData
    
    # -------------------------------------------------------------------------------------------------------

def user_update(body):
    # data에 user 관련 항목이 담겨온다
    
    data = json.loads(body.replace("'", "\""))   # string_to_dict 



    sql = "UPDATE BCDD.bcd_deposit set status = case"
    sql = sql + " WHEN actual_deposit_amount = plan_deposit_amount"
    sql = sql + " THEN '입금 완료' ELSE '미수' end WHERE project_id = "+data['project_id']+" and deposit_phase = "+data['deposit_phase']+";"

    cursor = conn.cursor()
    cnt = cursor.execute(sql)

    conn.commit()

    cursor.close()
    conn.close()

    return "Update OK"
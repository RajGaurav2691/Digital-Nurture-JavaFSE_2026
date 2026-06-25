SET SERVEROUTPUT ON;

--==========================================================
-- Scenario 1: Apply 1% Interest Rate Discount for Customers Above 60
--==========================================================

DECLARE
CURSOR c_customers IS
SELECT customer_id, age, loan_interest_rate
FROM customers;

BEGIN
FOR cust IN c_customers LOOP

        IF cust.age > 60 THEN

UPDATE customers
SET loan_interest_rate = loan_interest_rate - 1
WHERE customer_id = cust.customer_id;

DBMS_OUTPUT.PUT_LINE(
                'Discount applied to Customer ID: '
                || cust.customer_id
            );

END IF;

END LOOP;

COMMIT;

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
/

--==========================================================
-- Scenario 2: Promote Customers to VIP
--==========================================================

DECLARE
CURSOR c_customers IS
SELECT customer_id, balance
FROM customers;

BEGIN

FOR cust IN c_customers LOOP

        IF cust.balance > 10000 THEN

UPDATE customers
SET is_vip = 'TRUE'
WHERE customer_id = cust.customer_id;

DBMS_OUTPUT.PUT_LINE(
                'VIP Status Granted to Customer ID: '
                || cust.customer_id
            );

END IF;

END LOOP;

COMMIT;

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
/

--==========================================================
-- Scenario 3: Loan Due Reminder
--==========================================================

DECLARE
CURSOR c_loans IS
SELECT customer_id,
       loan_id,
       due_date
FROM loans
WHERE due_date BETWEEN SYSDATE AND SYSDATE + 30;

BEGIN

FOR loan_rec IN c_loans LOOP

        DBMS_OUTPUT.PUT_LINE(
            'Reminder: Loan ID '
            || loan_rec.loan_id
            || ' for Customer ID '
            || loan_rec.customer_id
            || ' is due on '
            || TO_CHAR(loan_rec.due_date, 'DD-MON-YYYY')
        );

END LOOP;

END;
/
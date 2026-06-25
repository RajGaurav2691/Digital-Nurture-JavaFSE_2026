SET SERVEROUTPUT ON;

--==========================================================
-- Scenario 1: Process Monthly Interest
--==========================================================

CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest
AS
BEGIN

UPDATE savings_accounts
SET balance = balance + (balance * 0.01);

COMMIT;

DBMS_OUTPUT.PUT_LINE(
        'Monthly interest processed successfully.'
    );

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE(
            'Error: ' || SQLERRM
        );
END;
/

-- Execution

BEGIN
    ProcessMonthlyInterest;
END;
/

--==========================================================
-- Scenario 2: Employee Bonus Update
--==========================================================

CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus
(
    p_department_id IN NUMBER,
    p_bonus_percent IN NUMBER
)
AS
BEGIN

UPDATE employees
SET salary = salary + (salary * p_bonus_percent / 100)
WHERE department_id = p_department_id;

COMMIT;

DBMS_OUTPUT.PUT_LINE(
        'Bonus updated successfully for Department '
        || p_department_id
    );

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE(
            'Error: ' || SQLERRM
        );
END;
/

-- Execution

BEGIN
    UpdateEmployeeBonus(10, 15);
END;
/

--==========================================================
-- Scenario 3: Fund Transfer
--==========================================================

CREATE OR REPLACE PROCEDURE TransferFunds
(
    p_source_account IN NUMBER,
    p_target_account IN NUMBER,
    p_amount         IN NUMBER
)
AS
    v_balance NUMBER;

BEGIN

SELECT balance
INTO v_balance
FROM accounts
WHERE account_id = p_source_account
    FOR UPDATE;

IF v_balance < p_amount THEN

        RAISE_APPLICATION_ERROR(
            -20001,
            'Insufficient Balance'
        );

END IF;

UPDATE accounts
SET balance = balance - p_amount
WHERE account_id = p_source_account;

UPDATE accounts
SET balance = balance + p_amount
WHERE account_id = p_target_account;

COMMIT;

DBMS_OUTPUT.PUT_LINE(
        'Transfer Successful. Amount: '
        || p_amount
    );

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE(
            'Transaction Failed: '
            || SQLERRM
        );

END;
/

-- Execution

BEGIN
    TransferFunds(
        p_source_account => 1001,
        p_target_account => 1002,
        p_amount         => 5000
    );
END;
/
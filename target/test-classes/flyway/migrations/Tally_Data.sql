

-- Method 3 - SQL Server 2008+ Row Construction
INSERT INTO users (first_name, last_name, email)
VALUES ('Dan', 'BoshDan', 'info@BoshDan.com'), ('Carol', 'Butters', 'sam@d1tm.org'), ('david', 'Biten', 'biten@district112.org'), ('Lisa', '.Twlight', 'sales@district113.org'), ('Randall', 'Bosher', 'info@BosherRandall.com'), ('Susan', 'Zutters', 'susan@d4tm.org'), ('Faid', 'Riddenzer', 'members@district142.org'), ('Cellesa', 'Dawne', 'recruitment@district163.org'), ('Jennifer', 'Daniels', 'hr@JenDaniels.com'), ('Sally', 'Walkers', 'sally@d133tm.org'), ('Dan', 'Givens', 'givens@district110.org'), ('Reese', 'Otter', 'otterR@district17.org')

;
select * from users;

-- Clean up
-- can't drop this table it's ref in user_goals
-- DROP TABLE users;
-- コメント
-- 実行前にpsqlのパスをPATHに追加しておくこと
-- 実行方法　psql --username=postgres -f C:\sql\create_mytest_for_PostgreSQL.sql

-- 販売DB削除
DROP DATABASE IF EXISTS mytest;
-- 販売DB作成
CREATE DATABASE mytest WITH ENCODING = 'UTF-8';

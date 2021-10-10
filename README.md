# DEMO人事管理システム

### フロントエンド
###### Port 8080
- HMTL5
- SASS
- Vue.js
  - Vuex
  - Vue Router
  - Sass Loader
  - axios

### バックエンド
###### Port 3000
- Spring Boot
  - Spring Boot DevTools
  - Spring Web
  - JDBC API
  - Spring Data JDBC
  - Mybatis Framework
  - MySQL Driver

### リレーショナルデータベース
###### Port 3306
- MySQL80

1. テーブル一覧  

|Tables_in_db_hrm|
| ------------- |
| field |
| hr_data | 
| post|
| section|

2. field テーブル

|Field| Type| Null|Key|Default|Extra|
| --- | --- | --- | --- | --- | --- |
| id         | varchar(255) | YES  |     | NULL    |     |
| name       | varchar(255) | YES  |     | NULL    |      |
| gender     | varchar(255) | YES  |    | NULL    |      |
| birthday   | varchar(255) | YES  |     | NULL    |     |
| other      | varchar(255) | YES  |     | NULL    |      |
| section_id | varchar(255) | YES  |     | NULL    |     |
| post_id    | varchar(255) | YES  |     | NULL    |      |

3. hr_data テーブル

| Field      | Type         | Null | Key | Default | Extra |
| --- | --- | --- | --- | --- | --- |
| id         | int          | NO   | PRI | NULL    |       |
| name       | varchar(255) | YES  |     | NULL    |       |
| gender     | varchar(255) | YES  |     | NULL    |       |
| birthday   | int          | YES  |     | NULL    |       |
| other      | varchar(255) | YES  |     | NULL    |       |
| section_id | int          | YES  |     | NULL    |       |
| post_id    | int          | YES  |     | NULL    |       |

4. post テーブル

| Field | Type         | Null | Key | Default | Extra          |
| --- | --- | --- | --- | --- | --- |
| id    | int          | NO   | PRI | NULL    | auto_increment |
| name  | varchar(255) | YES  |     | NULL    |                |

5. section テーブル

| Field | Type         | Null | Key | Default | Extra          |
| --- | --- | --- | --- | --- | --- |
| id    | int          | NO   | PRI | NULL    | auto_increment |
| name  | varchar(255) | YES  |     | NULL    |                |


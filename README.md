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
  - テーブル一覧
  +------------------+
  | Tables_in_db_hrm |
  +------------------+
  | field            |
  | hr_data          |　<= メインデータ
  | post             |
  | section          |
  +------------------+
    - field Table
    +------------+--------------+------+-----+---------+-------+
    | Field      | Type         | Null | Key | Default | Extra |
    +------------+--------------+------+-----+---------+-------+
    | id         | varchar(255) | YES  |     | NULL    |       |
    | name       | varchar(255) | YES  |     | NULL    |       |
    | gender     | varchar(255) | YES  |     | NULL    |       |
    | birthday   | varchar(255) | YES  |     | NULL    |       |
    | other      | varchar(255) | YES  |     | NULL    |       |
    | section_id | varchar(255) | YES  |     | NULL    |       |
    | post_id    | varchar(255) | YES  |     | NULL    |       |
    +------------+--------------+------+-----+---------+-------+
    - hr_data Table
    +------------+--------------+------+-----+---------+-------+
    | Field      | Type         | Null | Key | Default | Extra |
    +------------+--------------+------+-----+---------+-------+
    | id         | int          | NO   | PRI | NULL    |       |
    | name       | varchar(255) | YES  |     | NULL    |       |
    | gender     | varchar(255) | YES  |     | NULL    |       |
    | birthday   | int          | YES  |     | NULL    |       |
    | other      | varchar(255) | YES  |     | NULL    |       |
    | section_id | int          | YES  |     | NULL    |       |
    | post_id    | int          | YES  |     | NULL    |       |
    +------------+--------------+------+-----+---------+-------+
    - post Table
    +-------+--------------+------+-----+---------+----------------+
    | Field | Type         | Null | Key | Default | Extra          |
    +-------+--------------+------+-----+---------+----------------+
    | id    | int          | NO   | PRI | NULL    | auto_increment |
    | name  | varchar(255) | YES  |     | NULL    |                |
    +-------+--------------+------+-----+---------+----------------+
    - section Table
    +-------+--------------+------+-----+---------+----------------+
    | Field | Type         | Null | Key | Default | Extra          |
    +-------+--------------+------+-----+---------+----------------+
    | id    | int          | NO   | PRI | NULL    | auto_increment |
    | name  | varchar(255) | YES  |     | NULL    |                |
    +-------+--------------+------+-----+---------+----------------+


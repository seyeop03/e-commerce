CREATE DATABASE ecommerce;
USE ecommerce;

-- DDL
create table item(
	item_id bigint primary key auto_increment,
    name varchar(50),
    price bigint,
    manufacture_date varchar(50),
    origin varchar(50),
    company varchar(50),
    size varchar(50),
    color varchar(50)
);

create table member(
	member_id bigint primary key auto_increment,
    username varchar(50),
    password varchar(100),
    name varchar(50),
    birth varchar(50),
    phone varchar(50),
    email varchar(50),
    address varchar(60),
    home varchar(50),
    role varchar(20)
);

create table review(
	review_id bigint primary key auto_increment,
    member_id bigint,
    item_id bigint,
    stars tinyint,
    contents varchar(200),
    date date,
    FOREIGN KEY(member_id) REFERENCES member(member_id),
    FOREIGN KEY(item_id) REFERENCES item(item_id)
);

create table orders(
	order_id bigint primary key auto_increment,
    member_id bigint,
    date date,
    total_price bigint,
    status varchar(40),
    foreign key(member_id) references member(member_id)
);

create table order_item(
	order_item_id bigint primary key auto_increment,
    order_id bigint,
    item_id bigint,
    quantity integer,
    price bigint,
    foreign key(order_id) references orders(order_id),
    foreign key(item_id) references item(item_id)
);

create table category(
	category_id bigint primary key auto_increment,
    parent_category bigint,
    category_name varchar(40),
    foreign key(parent_category) references category(category_id)
);

create table category_item(
	category_item_id bigint primary key auto_increment,
    item_id bigint,
    category_id bigint,
    foreign key(item_id) references item(item_id),
    foreign key(category_id) references category(category_id)
);

/*
 * 멤버 테이블 데이터 추가
 */
INSERT INTO member (member_id, username, password, name, birth, phone, email, address, home, role)
VALUES
(1, 'admin', 'admin', '관리자', '1970-01-01', '010-1234-5678', 'admin@example.com', '서울특별시 강남구 테헤란로 123', '서울', 'ADMIN'),
(2, 'kim_sun', 'password002', '김선영', '1990-05-22', '010-2345-6789', 'kim.sun@example.com', '서울특별시 송파구 가락로 456', '서울', 'USER'),
(3, 'park_jung', 'password003', '박중환', '1982-11-30', '010-3456-7890', 'park.jung@example.com', '서울특별시 강동구 천호로 789', '서울', 'USER'),
(4, 'choi_min', 'password004', '최민수', '1995-09-10', '010-4567-8901', 'choi.min@example.com', '서울특별시 중구 명동길 101', '서울', 'USER'),
(5, 'kim_sun', 'password005', '김선영', '1992-12-12', '010-5678-9012', 'kim.sun2@example.com', '경기도 성남시 분당구 정자동 202', '경기도', 'USER'),
(6, 'lee_jin', 'password006', '이진호', '1985-01-15', '010-6789-0123', 'lee.jin2@example.com', '경기도 용인시 수지구 풍덕천로 303', '경기도', 'USER'),
(7, 'choi_min', 'password007', '최민수', '1995-09-10', '010-7890-1234', 'choi.min2@example.com', '경기도 고양시 일산동구 중앙로 404', '경기도', 'USER'),
(8, 'park_jung', 'password008', '박중환', '1982-11-30', '010-8901-2345', 'park.jung2@example.com', '경기도 화성시 동탄면 삼성로 505', '경기도', 'USER'),
(9, 'hong_ji', 'password009', '홍지수', '1988-07-07', '010-9012-3456', 'hong.ji@example.com', '부산광역시 해운대구 해운대해변로 606', '부산', 'USER'),
(10, 'jung_sook', 'password010', '정숙희', '1993-04-25', '010-0123-4567', 'jung.sook@example.com', '부산광역시 부산진구 서면로 707', '부산', 'USER'),
(11, 'yoon_jae', 'password011', '윤재훈', '1987-06-15', '010-1234-5678', 'yoon.jae@example.com', '부산광역시 남구 대연로 808', '부산', 'USER'),
(12, 'kim_jung', 'password012', '김정희', '1991-08-05', '010-2345-6789', 'kim.jung@example.com', '부산광역시 북구 금곡로 909', '부산', 'USER'),
(13, 'kim_jung', 'password013', '김정희', '1991-08-05', '010-3456-7890', 'kim.jung2@example.com', '대구광역시 중구 동성로 1010', '대구', 'USER'),
(14, 'hong_ji', 'password014', '홍지수', '1988-07-07', '010-4567-8901', 'hong.ji2@example.com', '대구광역시 수성구 범어로 1111', '대구', 'USER'),
(15, 'yoon_jae', 'password015', '윤재훈', '1987-06-15', '010-5678-9012', 'yoon.jae2@example.com', '대구광역시 달서구 월배로 1212', '대구', 'USER'),
(16, 'jung_sook', 'password016', '정숙희', '1993-04-25', '010-6789-0123', 'jung.sook2@example.com', '대구광역시 서구 평리로 1313', '대구', 'USER'),
(17, 'lee_jin', 'password017', '이진호', '1985-01-15', '010-7890-1234', 'lee.jin3@example.com', '광주광역시 서구 치평로 1414', '광주', 'USER'),
(18, 'kim_sun', 'password018', '김선영', '1990-05-22', '010-8901-2345', 'kim.sun3@example.com', '광주광역시 북구 용봉로 1515', '광주', 'USER'),
(19, 'choi_min', 'password019', '최민수', '1995-09-10', '010-9012-3456', 'choi.min3@example.com', '광주광역시 광산구 임방울대로 1616', '광주', 'USER'),
(20, 'park_jung', 'password020', '박중환', '1982-11-30', '010-0123-4567', 'park.jung3@example.com', '광주광역시 남구 임곡로 1717', '광주', 'USER'),
(21, 'yoon_jae', 'password021', '윤재훈', '1987-06-15', '010-1234-5678', 'yoon.jae3@example.com', '제주특별자치도 제주시 연삼로 1818', '제주', 'USER'),
(22, 'jung_sook', 'password022', '정숙희', '1993-04-25', '010-2345-6789', 'jung.sook3@example.com', '제주특별자치도 서귀포시 중앙로 1919', '제주', 'USER'),
(23, 'hong_ji', 'password023', '홍지수', '1988-07-07', '010-3456-7890', 'hong.ji3@example.com', '제주특별자치도 제주시 구좌읍 2020', '제주', 'USER'),
(24, 'kim_jung', 'password024', '김정희', '1991-08-05', '010-4567-8901', 'kim.jung3@example.com', '제주특별자치도 서귀포시 동홍동 2121', '제주', 'USER'),
(25, 'lee_jin', 'password025', '이진호', '1985-01-15', '010-5678-9012', 'lee.jin4@example.com', '서울특별시 강남구 테헤란로 123', '서울', 'USER'),
(26, 'kim_sun', 'password026', '김선영', '1990-05-22', '010-6789-0123', 'kim.sun4@example.com', '서울특별시 송파구 가락로 456', '서울', 'USER'),
(27, 'choi_min', 'password027', '최민수', '1995-09-10', '010-7890-1234', 'choi.min4@example.com', '서울특별시 중구 명동길 101', '서울', 'USER'),
(28, 'park_jung', 'password028', '박중환', '1982-11-30', '010-8901-2345', 'park.jung4@example.com', '서울특별시 강동구 천호로 789', '서울', 'USER'),
(29, 'yoon_jae', 'password029', '윤재훈', '1987-06-15', '010-9012-3456', 'yoon.jae4@example.com', '서울특별시 강남구 역삼로 303', '서울', 'USER'),
(30, 'jung_sook', 'password030', '정숙희', '1993-04-25', '010-0123-4567', 'jung.sook4@example.com', '서울특별시 송파구 오금로 404', '서울', 'USER'),
(31, 'user_smith01', 'password031', 'Smith John', '1988-02-19', '010-1111-2222', 'smith.john@example.com', '서울특별시 강남구 삼성로 333', '서울', 'USER'),
(32, 'user_jones01', 'password032', 'Jones Mary', '1991-06-22', '010-1234-5678', 'jones.mary@example.com', '서울특별시 종로구 종로 444', '서울', 'USER'),
(33, 'user_taylor01', 'password033', 'Taylor Robert', '1985-08-15', '010-2345-6789', 'taylor.robert@example.com', '서울특별시 서초구 반포대로 555', '서울', 'USER'),
(34, 'user_anderson01', 'password034', 'Anderson Emily', '1994-10-05', '010-3456-7890', 'anderson.emily@example.com', '서울특별시 중구 을지로 666', '서울', 'USER'),
(35, 'user_miller01', 'password035', 'Miller James', '1987-03-21', '010-4567-8901', 'miller.james@example.com', '서울특별시 강동구 성내동 777', '서울', 'USER'),
(36, 'user_wilson01', 'password036', 'Wilson Patricia', '1990-11-17', '010-5678-9012', 'wilson.patricia@example.com', '서울특별시 송파구 석촌호수로 888', '서울', 'USER'),
(37, 'user_davis01', 'password037', 'Davis Daniel', '1983-05-29', '010-6789-0123', 'davis.daniel@example.com', '서울특별시 마포구 월드컵로 999', '서울', 'USER'),
(38, 'user_garcia01', 'password038', 'Garcia Linda', '1995-12-01', '010-7890-1234', 'garcia.linda@example.com', '서울특별시 동작구 상도동 1010', '서울', 'USER'),
(39, 'user_rodriguez01', 'password039', 'Rodriguez Michael', '1989-07-14', '010-8901-2345', 'rodriguez.michael@example.com', '서울특별시 강서구 화곡로 1111', '서울', 'USER'),
(40, 'user_martinez01', 'password040', 'Martinez Jennifer', '1992-09-09', '010-9012-3456', 'martinez.jennifer@example.com', '서울특별시 노원구 공릉동 1212', '서울', 'USER'),
(41, 'user_clark01', 'password041', 'Clark William', '1984-04-04', '010-0123-4567', 'clark.william@example.com', '경기도 수원시 영통구 광교로 1313', '경기도', 'USER'),
(42, 'user_lewis01', 'password042', 'Lewis Jessica', '1993-01-23', '010-1234-5678', 'lewis.jessica@example.com', '경기도 용인시 기흥구 중부대로 1414', '경기도', 'USER'),
(43, 'user_lee05', 'password043', 'Lee David', '1986-08-30', '010-2345-6789', 'lee.david@example.com', '경기도 성남시 수정구 수정로 1515', '경기도', 'USER'),
(44, 'user_harris01', 'password044', 'Harris Sophia', '1991-11-17', '010-3456-7890', 'harris.sophia@example.com', '경기도 화성시 동탄면 동탄로 1616', '경기도', 'USER'),
(45, 'user_scott01', 'password045', 'Scott Charles', '1987-03-14', '010-4567-8901', 'scott.charles@example.com', '경기도 고양시 덕양구 행주로 1717', '경기도', 'USER'),
(46, 'user_morris01', 'password046', 'Morris Natalie', '1995-10-29', '010-5678-9012', 'morris.natalie@example.com', '경기도 파주시 광탄면 1818', '경기도', 'USER'),
(47, 'user_brown01', 'password047', 'Brown Steven', '1992-05-22', '010-6789-0123', 'brown.steven@example.com', '경기도 양주시 양주로 1919', '경기도', 'USER'),
(48, 'user_johnson01', 'password048', 'Johnson Olivia', '1989-12-01', '010-7890-1234', 'johnson.olivia@example.com', '경기도 남양주시 금곡로 2020', '경기도', 'USER'),
(49, 'user_thomas01', 'password049', 'Thomas George', '1994-07-30', '010-8901-2345', 'thomas.george@example.com', '경기도 광명시 광명로 2121', '경기도', 'USER'),
(50, 'user_walker01', 'password050', 'Walker Emma', '1990-03-15', '010-9012-3456', 'walker.emma@example.com', '경기도 안양시 동안구 평촌대로 2222', '경기도', 'USER');

/*
 * 카테고리 테이블 데이터 추가
 */
INSERT INTO category(parent_category, category_name) VALUES
(null, '가전/디지털'),
(null, '반려동물용품'),
(null, '의류'),
(null, '식품'),
(null, '생활용품'),
(null, '스포츠/레져'),
(null, '문구/완구'),
(null, '도서');

INSERT INTO category(parent_category, category_name) VALUES
(1, '냉장고'),(1, 'TV'),(1, '세탁기'),(1, '청소기'),(1, '전자레인지'),(1, '컴퓨터'),
(2, '사료'),(2, '간식'),(2, '용품'),
(3, '상의'),(3, '하의'),(3, '신발'),(3, '속옷'),(3, '점퍼'),(3, '가방/악세서리'),
(4, '과일'),(4, '축산/계란'),(4, '수산물/건어물'),(4, '냉장/냉동/간편요리'),(4, '쌀/잡곡'),(4, '커피/원두/차'),
(5, '잡화'),(5, '세제'),(5, '수납/정리'),(5, '바디/헤어/구강/면도'),(5, '가구/조명/인테리어'),
(6, '구기'),(6, '헬스/요가'),(6, '라켓스포츠'),(6, '수영'),(6, '낚시'),
(7, '필기구'),(7, '노트/메모지'),(7, '유아'),
(8, '소설'),(8, '경제/경영'),(8, 'IT'),(8, '예술'),(8, '기술/공학'),(8, '유아');
select * from category;
/*
 * 상품 테이블 데이터 추가
 */
-- Refrigerators Data
INSERT INTO item(item_id, name, price, manufacture_date, origin, company, size, color)
VALUES
(1, '삼성 패밀리 허브', 3200000, '2023-04-10', 'South Korea', 'Samsung', '680L', 'Silver'),
(2, 'LG 인스타뷰', 2800000, '2022-09-12', 'South Korea', 'LG', '650L', 'Black'),
(3, 'Whirlpool WRS588FIHZ', 2000000, '2021-12-30', 'USA', 'Whirlpool', '560L', 'White'),
(4, 'Haier HRF-622KS', 1800000, '2023-06-11', 'China', 'Haier', '580L', 'Gray'),
(5, 'Bosch KAD92HBFP', 3500000, '2023-03-17', 'Germany', 'Bosch', '700L', 'Black'),
(6, 'Daewoo FRS-T22B', 1700000, '2022-11-20', 'South Korea', 'Daewoo', '520L', 'White'),
(7, 'Panasonic NR-BY602XS', 2200000, '2023-05-01', 'Japan', 'Panasonic', '600L', 'Gray'),
(8, 'Hitachi R-WB640V', 2500000, '2022-12-12', 'Japan', 'Hitachi', '640L', 'Silver'),
(9, 'Electrolux EQE7000', 2100000, '2023-01-28', 'Sweden', 'Electrolux', '700L', 'Black'),
(10, 'Hisense RS694N4TC2', 1600000, '2023-07-19', 'China', 'Hisense', '600L', 'Stainless Steel');

-- TVs Data
INSERT INTO item(item_id, name, price, manufacture_date, origin, company, size, color)
VALUES
(11, '삼성 QN90B', 2200000, '2023-02-15', 'South Korea', 'Samsung', '55 inch', 'Black'),
(12, 'LG OLED C1', 2400000, '2022-10-05', 'South Korea', 'LG', '65 inch', 'Silver'),
(13, 'Sony Bravia XR', 2300000, '2023-05-22', 'Japan', 'Sony', '55 inch', 'Gray'),
(14, 'TCL 6-Series', 1600000, '2022-09-14', 'China', 'TCL', '55 inch', 'Black'),
(15, 'Vizio P-Series', 1900000, '2023-01-10', 'USA', 'Vizio', '65 inch', 'Silver'),
(16, 'Panasonic OLED HZ2000', 3000000, '2023-04-30', 'Japan', 'Panasonic', '65 inch', 'Black'),
(17, 'Hisense U8G', 1500000, '2022-11-25', 'China', 'Hisense', '55 inch', 'Black'),
(18, 'Philips OLED 806', 2800000, '2023-06-18', 'Netherlands', 'Philips', '65 inch', 'Silver'),
(19, 'Sharp AQUOS R5', 2100000, '2022-08-30', 'Japan', 'Sharp', '60 inch', 'Black'),
(20, 'LG NanoCell 90 Series', 1700000, '2023-07-09', 'South Korea', 'LG', '55 inch', 'Black');

-- Washing Machines Data
INSERT INTO item(item_id, name, price, manufacture_date, origin, company, size, color)
VALUES
(21, 'LG 트윈워시', 1200000, '2023-03-16', 'South Korea', 'LG', '15kg', 'White'),
(22, '삼성 애드워시', 1100000, '2022-10-11', 'South Korea', 'Samsung', '12kg', 'Silver'),
(23, 'Whirlpool WFW9620HC', 1300000, '2023-04-08', 'USA', 'Whirlpool', '10kg', 'Gray'),
(24, 'Bosch WAW325H0GB', 1400000, '2022-12-19', 'Germany', 'Bosch', '9kg', 'White'),
(25, 'Haier HWD80', 1000000, '2023-02-26', 'China', 'Haier', '8kg', 'Black'),
(26, 'Panasonic NA-D106X1', 1500000, '2023-05-14', 'Japan', 'Panasonic', '10kg', 'Silver'),
(27, 'Electrolux EWF8024', 900000, '2022-11-22', 'Sweden', 'Electrolux', '8kg', 'White'),
(28, 'Daewoo DWD-FT1121', 950000, '2023-06-10', 'South Korea', 'Daewoo', '11kg', 'Gray'),
(29, 'Hitachi BD-SG100', 1400000, '2023-01-05', 'Japan', 'Hitachi', '10kg', 'Silver'),
(30, 'Miele WWR860 WPS', 2200000, '2023-07-01', 'Germany', 'Miele', '9kg', 'White');

-- Vacuum Cleaners Data
INSERT INTO item(item_id, name, price, manufacture_date, origin, company, size, color)
VALUES
(31, 'Dyson V15 Detect', 1000000, '2023-02-20', 'UK', 'Dyson', '2L', 'Yellow'),
(32, '삼성 제트 90', 800000, '2022-11-14', 'South Korea', 'Samsung', '1.8L', 'Silver'),
(33, 'LG 코드제로 A9', 750000, '2023-03-07', 'South Korea', 'LG', '1.7L', 'White'),
(34, 'Xiaomi Mi 로봇 청소기', 500000, '2023-04-25', 'China', 'Xiaomi', '3L', 'Black'),
(35, 'Shark IZ163H', 600000, '2022-12-10', 'USA', 'Shark', '2L', 'Gray'),
(36, 'Ecovacs Deebot N8', 550000, '2023-06-30', 'China', 'Ecovacs', '2.5L', 'White'),
(37, 'iRobot Roomba 980', 850000, '2023-01-12', 'USA', 'iRobot', '3L', 'Black'),
(38, 'Panasonic MC-SB85K', 780000, '2022-09-28', 'Japan', 'Panasonic', '2L', 'Silver'),
(39, 'Philips PowerPro Aqua', 700000, '2023-05-17', 'Netherlands', 'Philips', '1.5L', 'Blue'),
(40, 'Tineco A11 Hero', 650000, '2023-07-22', 'China', 'Tineco', '1.8L', 'Red');

-- Microwave Ovens Data
INSERT INTO item(item_id, name, price, manufacture_date, origin, company, size, color)
VALUES
(41, '삼성 MG23K3575AW', 200000, '2023-04-30', 'South Korea', 'Samsung', '23L', 'White'),
(42, 'LG 네오셰프', 250000, '2022-09-18', 'South Korea', 'LG', '25L', 'Black'),
(43, 'Panasonic NN-SN966S', 270000, '2023-02-14', 'Japan', 'Panasonic', '30L', 'Silver'),
(44, 'Sharp R-21LCFS', 220000, '2023-05-22', 'Japan', 'Sharp', '20L', 'White'),
(45, 'Whirlpool WMH31017HS', 180000, '2022-11-04', 'USA', 'Whirlpool', '17L', 'Black'),
(46, 'Toshiba EM925A5A', 150000, '2023-06-10', 'Japan', 'Toshiba', '25L', 'Silver'),
(47, 'Bosch BEL554MS0', 300000, '2023-03-15', 'Germany', 'Bosch', '30L', 'Gray'),
(48, 'Daewoo KOR-7LREW', 120000, '2022-08-25', 'South Korea', 'Daewoo', '20L', 'White'),
(49, 'Midea MM720C2GS', 170000, '2023-07-28', 'China', 'Midea', '22L', 'Silver'),
(50, 'Hisense HMV6033', 160000, '2023-01-09', 'China', 'Hisense', '23L', 'Black');

-- Computers Data
INSERT INTO item(item_id, name, price, manufacture_date, origin, company, size, color)
VALUES
(51, 'Apple MacBook Pro 16', 3500000, '2023-02-12', 'USA', 'Apple', '16 inch', 'Space Gray'),
(52, 'Dell XPS 13', 2000000, '2022-10-23', 'USA', 'Dell', '13 inch', 'Silver'),
(53, 'HP Spectre x360', 2100000, '2023-05-05', 'USA', 'HP', '14 inch', 'Nightfall Black'),
(54, 'Lenovo ThinkPad X1 Carbon', 2400000, '2023-03-10', 'China', 'Lenovo', '14 inch', 'Black'),
(55, 'Asus ZenBook 14', 1800000, '2022-12-15', 'Taiwan', 'Asus', '14 inch', 'Blue'),
(56, 'Microsoft Surface Laptop 4', 2200000, '2023-01-05', 'USA', 'Microsoft', '13.5 inch', 'Platinum'),
(57, 'Acer Swift 3', 1600000, '2023-04-21', 'Taiwan', 'Acer', '14 inch', 'Silver'),
(58, 'Razer Blade 15', 3200000, '2022-09-17', 'USA', 'Razer', '15 inch', 'Black'),
(59, 'MSI GS66 Stealth', 2900000, '2023-07-11', 'Taiwan', 'MSI', '15.6 inch', 'Black'),
(60, 'Gigabyte AERO 15', 2700000, '2023-06-08', 'Taiwan', 'Gigabyte', '15.6 inch', 'Silver');


-- 사료 데이터
INSERT INTO item(item_id, name, price, manufacture_date, origin, company, size, color)
VALUES
(61, '로얄캐닌 강아지 사료', 50000, '2023-03-15', 'France', 'Royal Canin', '2kg', NULL),
(62, '네이처스 버라이어티 인스팅트', 60000, '2023-05-10', 'USA', 'Nature\s Variety', '3kg', NULL),
(63, 'ANF 오리지날 강아지 사료', 45000, '2023-01-20', 'South Korea', 'ANF', '2.5kg', NULL),
(64, '뉴트리나 건강백서', 55000, '2022-11-18', 'South Korea', 'Nutrina', '3kg', NULL),
(65, '블루버팔로 치킨 레시피', 70000, '2023-02-28', 'USA', 'Blue Buffalo', '4kg', NULL),
(66, '퓨리나 원', 40000, '2023-06-12', 'USA', 'Purina', '2kg', NULL),
(67, '고! 카나겐', 75000, '2023-07-07', 'Canada', 'Go! Carnivore', '3kg', NULL),
(68, '오리젠 오리지날', 80000, '2023-04-05', 'Canada', 'Orijen', '2.5kg', NULL),
(69, '힐스 사이언스 다이어트', 65000, '2023-03-01', 'USA', 'Hill\s', '3kg', NULL),
(70, '아이ams 건강관리 사료', 55000, '2022-12-10', 'South Korea', 'Iams', '2kg', NULL);

-- 간식 데이터
INSERT INTO item(item_id, name, price, manufacture_date, origin, company, size, color)
VALUES
(71, '덴탈케어 덴탈츄', 12000, '2023-02-15', 'South Korea', 'Dental Care', '500g', NULL),
(72, '몽디프리 리얼 덴탈껌', 15000, '2023-06-01', 'South Korea', 'Mondipet', '300g', NULL),
(73, '펫드라이 간식바', 18000, '2023-05-12', 'USA', 'Petdry', '400g', NULL),
(74, '헬로도기 소프트 간식', 10000, '2023-03-20', 'South Korea', 'Hello Doggy', '250g', NULL),
(75, '베게브랜드 강아지 간식', 20000, '2023-04-05', 'France', 'Begebrand', '500g', NULL),
(76, 'ANF 치킨 저키', 22000, '2023-07-18', 'South Korea', 'ANF', '450g', NULL),
(77, '로얄캐닌 간식', 17000, '2023-01-10', 'France', 'Royal Canin', '300g', NULL),
(78, '네추럴코어 오리고기 간식', 25000, '2023-06-22', 'South Korea', 'Natural Core', '500g', NULL),
(79, '닥터독 연어 저키', 19000, '2023-02-25', 'South Korea', 'Dr. Dog', '350g', NULL),
(80, '그레인프리 오리 간식', 21000, '2023-03-19', 'USA', 'Grain-Free', '400g', NULL);

-- 용품 데이터
INSERT INTO item(item_id, name, price, manufacture_date, origin, company, size, color)
VALUES
(81, '아이리스 강아지 집', 75000, '2023-05-10', 'Japan', 'Iris', 'Medium', NULL),
(82, '도그타임 발판 매트', 30000, '2023-01-30', 'South Korea', 'Dogtime', 'Large', NULL),
(83, '패티움 훈련 패드', 40000, '2022-11-18', 'South Korea', 'Pettium', '60 Sheets', NULL),
(84, '헬로도기 산책줄', 25000, '2023-06-20', 'South Korea', 'Hello Doggy', 'Size S', NULL),
(85, '이케아 펫베드', 45000, '2023-03-07', 'Sweden', 'IKEA', 'Medium', NULL),
(86, '네추럴코어 강아지 방석', 50000, '2022-12-15', 'South Korea', 'Natural Core', 'Large', NULL),
(87, '로얄펫 물그릇', 15000, '2023-04-14', 'South Korea', 'Royal Pet', '500ml', NULL),
(88, '플라워펫 식기세트', 35000, '2023-02-18', 'South Korea', 'Flower Pet', '2-Piece Set', NULL),
(89, '몽디프리 휴대용 물병', 20000, '2023-05-30', 'South Korea', 'Mondipet', '500ml', NULL),
(90, '아웃워드하운드 강아지 장난감', 25000, '2023-07-12', 'USA', 'Outward Hound', 'Small', NULL);

-- 상의 데이터
INSERT INTO item(item_id, name, price, manufacture_date, origin, company, size, color)
VALUES
(91, '나이키 에어 맥스 티셔츠', 35000, '2023-08-15', 'USA', 'Nike', 'M', 'Black'),
(92, '아디다스 클라이마쿨 폴로', 40000, '2023-07-22', 'USA', 'Adidas', 'L', 'White'),
(93, '유니클로 오버사이즈 셔츠', 25000, '2023-06-10', 'Japan', 'Uniqlo', 'M', 'Gray'),
(94, 'ZARA 기본 티셔츠', 30000, '2023-05-15', 'Spain', 'ZARA', 'S', 'Navy'),
(95, 'H&M 린넨 블라우스', 27000, '2023-07-05', 'Sweden', 'H&M', 'L', 'Beige'),
(96, '폴로 랄프 로렌 체크 셔츠', 50000, '2023-04-20', 'USA', 'Polo Ralph Lauren', 'M', 'Check'),
(97, '베르사체 프린트 티셔츠', 60000, '2023-03-18', 'Italy', 'Versace', 'L', 'Pink'),
(98, '토리버치 카디건', 55000, '2023-02-28', 'USA', 'Tory Burch', 'S', 'Blue'),
(99, '라코스테 스트라이프 셔츠', 45000, '2023-01-15', 'France', 'Lacoste', 'M', 'Striped'),
(100, '버버리 체크 티셔츠', 70000, '2023-07-12', 'UK', 'Burberry', 'L', 'Check');

-- 하의 데이터
INSERT INTO item(item_id, name, price, manufacture_date, origin, company, size, color)
VALUES
(101, '리바이스 501 청바지', 60000, '2023-08-01', 'USA', 'Levi\s', '32', 'Blue'),
(102, '유니클로 슬림핏 진', 35000, '2023-06-25', 'Japan', 'Uniqlo', '30', 'Dark Blue'),
(103, 'H&M 코튼 바지', 27000, '2023-07-18', 'Sweden', 'H&M', '32', 'Gray'),
(104, '나이키 조거 팬츠', 40000, '2023-05-10', 'USA', 'Nike', 'L', 'Black'),
(105, 'ZARA 하이웨이스트 팬츠', 45000, '2023-04-12', 'Spain', 'ZARA', 'S', 'Cream'),
(106, '아디다스 트랙 팬츠', 38000, '2023-03-20', 'USA', 'Adidas', 'M', 'Navy'),
(107, '폴로 랄프 로렌 청바지', 55000, '2023-02-15', 'USA', 'Polo Ralph Lauren', '34', 'Blue'),
(108, '버버리 체크 스커트', 70000, '2023-01-10', 'UK', 'Burberry', 'M', 'Check'),
(109, '락포트 슬랙스', 50000, '2023-07-05', 'USA', 'Rockport', 'L', 'Black'),
(110, 'Mango 면 바지', 34000, '2023-06-30', 'Spain', 'Mango', '32', 'Beige');

-- 신발 데이터
INSERT INTO item(item_id, name, price, manufacture_date, origin, company, size, color)
VALUES
(111, '나이키 에어포스 1', 80000, '2023-05-20', 'USA', 'Nike', '270mm', 'White'),
(112, '아디다스 울트라부스트', 120000, '2023-07-12', 'USA', 'Adidas', '260mm', 'Black'),
(113, '컨버스 척테일러 올스타', 60000, '2023-04-18', 'USA', 'Converse', '280mm', 'Navy'),
(114, '반스 클래식 슬립온', 70000, '2023-03-25', 'USA', 'Vans', '265mm', 'Check'),
(115, '뉴발란스 990v5', 150000, '2023-02-05', 'USA', 'New Balance', '275mm', 'Gray'),
(116, '프라다 스니커즈', 200000, '2023-01-15', 'Italy', 'Prada', '270mm', 'Black'),
(117, '구찌 GG 로고 스니커즈', 250000, '2023-06-20', 'Italy', 'Gucci', '275mm', 'White'),
(118, '모스키노 하이탑 스니커즈', 180000, '2023-08-05', 'Italy', 'Moschino', '260mm', 'Pink'),
(119, '디올 B23 스니커즈', 220000, '2023-07-18', 'France', 'Dior', '280mm', 'Blue'),
(120, '발렌시아가 트리플 S', 300000, '2023-05-10', 'Spain', 'Balenciaga', '285mm', 'Gray');

-- 속옷 데이터
INSERT INTO item(item_id, name, price, manufacture_date, origin, company, size, color)
VALUES
(121, '비비안 속옷 세트', 30000, '2023-03-12', 'Korea', 'Vivian', 'M', 'Black'),
(122, '시리즈 파운데이션 브라', 25000, '2023-07-15', 'Korea', 'Series', 'S', 'White'),
(123, '삼성 속옷 세트', 28000, '2023-06-05', 'Korea', 'Samsung', 'L', 'Navy'),
(124, '마이클코어스 브라', 35000, '2023-05-10', 'USA', 'Michael Kors', 'M', 'Pink'),
(125, '아망다 속옷', 27000, '2023-08-20', 'France', 'Amanda', 'S', 'Beige'),
(126, '플라스틱 인서트 팬티', 30000, '2023-07-25', 'Korea', 'Plastic', 'L', 'Black'),
(127, '프라다 란제리', 50000, '2023-04-15', 'Italy', 'Prada', 'M', 'White'),
(128, '로로브래지어', 33000, '2023-06-10', 'USA', 'Loro', 'S', 'Red'),
(129, '블루밍 속옷 세트', 29000, '2023-02-05', 'Korea', 'Blooming', 'L', 'Blue'),
(130, '벨루가 브라', 32000, '2023-01-15', 'USA', 'Beluga', 'M', 'Purple');

-- 점퍼 데이터
INSERT INTO item(item_id, name, price, manufacture_date, origin, company, size, color)
VALUES
(131, '나이키 윈드러너 점퍼', 120000, '2023-08-05', 'USA', 'Nike', 'M', 'Black'),
(132, '아디다스 오버사이즈 점퍼', 130000, '2023-06-12', 'USA', 'Adidas', 'L', 'Navy'),
(133, '유니클로 플리스 점퍼', 80000, '2023-05-22', 'Japan', 'Uniqlo', 'M', 'Gray'),
(134, 'H&M 롱 점퍼', 95000, '2023-07-10', 'Sweden', 'H&M', 'L', 'Black'),
(135, '폴로 랄프 로렌 하이넥 점퍼', 150000, '2023-04-18', 'USA', 'Polo Ralph Lauren', 'M', 'Navy'),
(136, '프라다 가죽 점퍼', 200000, '2023-01-25', 'Italy', 'Prada', 'L', 'Brown'),
(137, '캐나다 구스 퍼버 점퍼', 170000, '2023-02-20', 'Canada', 'Canada Goose', 'M', 'White'),
(138, '알파 인더스트리 봄버 점퍼', 110000, '2023-03-15', 'USA', 'Alpha Industries', 'L', 'Olive'),
(139, '버버리 트렌치코트', 140000, '2023-04-10', 'UK', 'Burberry', 'S', 'Beige'),
(140, '코오롱 스포츠 윈드브레이커', 90000, '2023-05-05', 'South Korea', 'Kolon Sport', 'M', 'Black');

-- 가방/악세사리 데이터
INSERT INTO item(item_id, name, price, manufacture_date, origin, company, size, color)
VALUES
(141, '구찌 핸드백', 150000, '2023-07-05', 'Italy', 'Gucci', 'Medium', NULL),
(142, '투미 백팩', 180000, '2023-06-15', 'USA', 'Tumi', 'Large', NULL),
(143, '루이비통 지갑', 50000, '2023-08-10', 'France', 'Louis Vuitton', 'Small', NULL),
(144, '미우미우 선글라스', 70000, '2023-05-20', 'Italy', 'Miu Miu', 'One Size', 'Gold'),
(145, '프라다 벨트', 85000, '2023-03-15', 'Italy', 'Prada', 'One Size', 'Silver'),
(146, '루이비통 스카프', 120000, '2023-07-25', 'France', 'Louis Vuitton', 'One Size', 'Gold'),
(147, '구찌 모자', 90000, '2023-04-10', 'Italy', 'Gucci', 'One Size', 'Silver'),
(148, '셀린느 클러치', 200000, '2023-01-12', 'France', 'Céline', 'Medium', NULL),
(149, '디올 가방', 250000, '2023-08-01', 'France', 'Dior', 'Large', NULL),
(150, '발렌시아가 지갑', 60000, '2023-02-25', 'Spain', 'Balenciaga', 'Small', NULL);

-- 과일 데이터
INSERT INTO item(item_id, name, price, manufacture_date, origin, company, size, color)
VALUES
(151, '사과', 3000, '2024-09-01', 'South Korea', '농협', '1kg', NULL),
(152, '배', 4000, '2024-09-05', 'South Korea', '농협', '1kg', NULL),
(153, '바나나', 2000, '2024-09-10', 'Philippines', 'Dole', '1kg', NULL),
(154, '오렌지', 3500, '2024-09-07', 'USA', 'Sunkist', '1kg', NULL),
(155, '포도', 4500, '2024-09-12', 'South Korea', '농협', '1kg', NULL),
(156, '키위', 5000, '2024-09-15', 'New Zealand', 'Zespri', '1kg', NULL),
(157, '딸기', 6000, '2024-09-08', 'South Korea', '농협', '500g', NULL),
(158, '망고', 7000, '2024-09-11', 'Thailand', 'Chok Chai', '1kg', NULL),
(159, '파인애플', 5500, '2024-09-06', 'Philippines', 'Dole', '1kg', NULL),
(160, '체리', 8000, '2024-09-09', 'USA', 'Rainier', '500g', NULL);

-- 축산 데이터
INSERT INTO item(item_id, name, price, manufacture_date, origin, company, size, color)
VALUES
(161, '한우 등심', 30000, '2024-09-02', 'South Korea', '한우농장', '200g', NULL),
(162, '삼겹살', 15000, '2024-09-03', 'South Korea', '돼지농장', '300g', NULL),
(163, '닭가슴살', 12000, '2024-09-04', 'South Korea', '닭고기농장', '200g', NULL),
(164, '계란', 5000, '2024-09-01', 'South Korea', '농협', '10개', NULL),
(165, '소고기 갈비', 28000, '2024-09-02', 'South Korea', '한우농장', '300g', NULL),
(166, '양고기', 35000, '2024-09-05', 'Australia', 'MeatCo', '200g', NULL),
(167, '닭날개', 10000, '2024-09-06', 'South Korea', '닭고기농장', '300g', NULL),
(168, '햄', 8000, '2024-09-07', 'South Korea', '햄제작소', '200g', NULL),
(169, '소고기 안심', 33000, '2024-09-08', 'South Korea', '한우농장', '200g', NULL),
(170, '계란 흰자', 6000, '2024-09-09', 'South Korea', '농협', '10개', NULL);

-- 수산물/건어물 데이터
INSERT INTO item(item_id, name, price, manufacture_date, origin, company, size, color)
VALUES
(171, '고등어', 6000, '2024-09-10', 'South Korea', '수산시장', '200g', NULL),
(172, '문어', 10000, '2024-09-11', 'South Korea', '수산시장', '300g', NULL),
(173, '오징어', 8000, '2024-09-12', 'South Korea', '수산시장', '200g', NULL),
(174, '멸치', 7000, '2024-09-13', 'South Korea', '수산시장', '200g', NULL),
(175, '건새우', 12000, '2024-09-14', 'South Korea', '수산시장', '150g', NULL),
(176, '참치 캔', 3500, '2024-09-15', 'USA', 'StarKist', '200g', NULL),
(177, '건다시마', 4000, '2024-09-16', 'Japan', 'Marukome', '100g', NULL),
(178, '건오징어', 9000, '2024-09-17', 'South Korea', '수산시장', '100g', NULL),
(179, '건미역', 5000, '2024-09-18', 'South Korea', '수산시장', '100g', NULL),
(180, '건조 표고버섯', 8000, '2024-09-19', 'South Korea', '농장', '50g', NULL);

-- 냉동/냉장/간편요리 데이터
INSERT INTO item(item_id, name, price, manufacture_date, origin, company, size, color)
VALUES
(181, '냉동 피자', 8000, '2024-09-01', 'South Korea', '피자마켓', '1판', NULL),
(182, '냉동 만두', 6000, '2024-09-02', 'South Korea', '만두나라', '1봉', NULL),
(183, '냉동 생선', 7000, '2024-09-03', 'South Korea', '수산시장', '500g', NULL),
(184, '냉장 유산균', 4000, '2024-09-04', 'South Korea', '유산균팜', '500ml', NULL),
(185, '냉장 소시지', 5000, '2024-09-05', 'South Korea', '소시지팩토리', '200g', NULL),
(186, '간편식 비빔밥', 7000, '2024-09-06', 'South Korea', '간편식품', '1인분', NULL),
(187, '냉동 새우', 9000, '2024-09-07', 'South Korea', '수산시장', '300g', NULL),
(188, '냉장 요구르트', 3500, '2024-09-08', 'South Korea', '유산균팜', '500ml', NULL),
(189, '냉동 볶음밥', 8000, '2024-09-09', 'South Korea', '간편식품', '1인분', NULL),
(190, '냉장 치즈', 6000, '2024-09-10', 'South Korea', '치즈팩토리', '200g', NULL);

-- 쌀/잡곡 데이터
INSERT INTO item(item_id, name, price, manufacture_date, origin, company, size, color)
VALUES
(191, '쌀 10kg', 25000, '2024-09-01', 'South Korea', '농협', '10kg', NULL),
(192, '현미 5kg', 20000, '2024-09-02', 'South Korea', '농협', '5kg', NULL),
(193, '잡곡 혼합 3kg', 18000, '2024-09-03', 'South Korea', '농협', '3kg', NULL),
(194, '찹쌀 5kg', 22000, '2024-09-04', 'South Korea', '농협', '5kg', NULL),
(195, '보리 2kg', 15000, '2024-09-05', 'South Korea', '농협', '2kg', NULL),
(196, '콩 1kg', 13000, '2024-09-06', 'South Korea', '농협', '1kg', NULL),
(197, '흑미 3kg', 17000, '2024-09-07', 'South Korea', '농협', '3kg', NULL),
(198, '옥수수 2kg', 14000, '2024-09-08', 'South Korea', '농협', '2kg', NULL),
(199, '율무 1kg', 16000, '2024-09-09', 'South Korea', '농협', '1kg', NULL),
(200, '기장 1kg', 15000, '2024-09-10', 'South Korea', '농협', '1kg', NULL);

-- 커피/원두/차 데이터
INSERT INTO item(item_id, name, price, manufacture_date, origin, company, size, color)
VALUES
(201, '아메리카노 원두', 25000, '2024-09-01', 'Colombia', 'Cafe Latte', '250g', NULL),
(202, '에스프레소 원두', 27000, '2024-09-02', 'Brazil', 'Espresso House', '250g', NULL),
(203, '드립커피', 15000, '2024-09-03', 'South Korea', 'Coffee & Dream', '100g', NULL),
(204, '그린티', 12000, '2024-09-04', 'Japan', 'Oriental', '100g', NULL),
(205, '홍차', 14000, '2024-09-05', 'India', 'Teapot', '100g', NULL),
(206, '카페라떼 믹스', 16000, '2024-09-06', 'South Korea', 'Cafe Latte', '1kg', NULL),
(207, '모카 원두', 28000, '2024-09-07', 'Ethiopia', 'Mocha Coffee', '250g', NULL),
(208, '얼그레이', 15000, '2024-09-08', 'UK', 'Teapot', '100g', NULL),
(209, '콜드브루 커피', 22000, '2024-09-09', 'South Korea', 'Cold Brew Factory', '200ml', NULL),
(210, '허브티', 13000, '2024-09-10', 'USA', 'Herb Tea Shop', '100g', NULL);

-- 잡화 데이터
INSERT INTO item(item_id, name, price, manufacture_date, origin, company, size, color)
VALUES
(211, '가방', 50000, '2024-09-01', 'South Korea', 'BagFactory', NULL, NULL),
(212, '지갑', 25000, '2024-09-02', 'South Korea', 'WalletWorks', NULL, NULL),
(213, '모자', 15000, '2024-09-03', 'South Korea', 'HatHouse', NULL, NULL),
(214, '벨트', 20000, '2024-09-04', 'South Korea', 'BeltBoutique', NULL, NULL),
(215, '장갑', 12000, '2024-09-05', 'South Korea', 'GloveGear', NULL, NULL),
(216, '우산', 18000, '2024-09-06', 'South Korea', 'UmbrellaCo', NULL, NULL),
(217, '핸드크림', 10000, '2024-09-07', 'South Korea', 'SkinCare', NULL, NULL),
(218, '키홀더', 8000, '2024-09-08', 'South Korea', 'KeyKing', NULL, NULL),
(219, '목걸이', 35000, '2024-09-09', 'South Korea', 'NecklaceNook', NULL, NULL),
(220, '귀걸이', 22000, '2024-09-10', 'South Korea', 'EarringEmporium', NULL, NULL);

-- 세제 데이터
INSERT INTO item(item_id, name, price, manufacture_date, origin, company, size, color)
VALUES
(221, '세탁세제', 8000, '2024-09-01', 'South Korea', 'CleanCo', '2L', NULL),
(222, '주방세제', 5000, '2024-09-02', 'South Korea', 'DishCleaner', '500ml', NULL),
(223, '욕실세제', 6000, '2024-09-03', 'South Korea', 'BathClean', '1L', NULL),
(224, '다목적 세제', 7000, '2024-09-04', 'South Korea', 'MultiClean', '1.5L', NULL),
(225, '유리세정제', 4000, '2024-09-05', 'South Korea', 'GlassShine', '500ml', NULL),
(226, '천연세제', 9000, '2024-09-06', 'South Korea', 'EcoClean', '1L', NULL),
(227, '세탁표백제', 10000, '2024-09-07', 'South Korea', 'BleachPlus', '1L', NULL),
(228, '에어컨 세제', 12000, '2024-09-08', 'South Korea', 'ACCleaner', '500ml', NULL),
(229, '자동차 세제', 11000, '2024-09-09', 'South Korea', 'CarWash', '1L', NULL),
(230, '기름때 제거제', 13000, '2024-09-10', 'South Korea', 'GreaseAway', '500ml', NULL);

-- 수납/정리 데이터
INSERT INTO item(item_id, name, price, manufacture_date, origin, company, size, color)
VALUES
(231, '수납박스', 15000, '2024-09-01', 'South Korea', 'StorageBoxCo', '30x30x30 cm', NULL),
(232, '정리함', 20000, '2024-09-02', 'South Korea', 'OrganizerWorks', '40x20x20 cm', NULL),
(233, '옷걸이', 8000, '2024-09-03', 'South Korea', 'HangerHouse', '50 pcs', NULL),
(234, '서랍장', 60000, '2024-09-04', 'South Korea', 'DrawerDepot', '4 drawers', NULL),
(235, '정리용 바구니', 12000, '2024-09-05', 'South Korea', 'BasketWorld', '1 basket', NULL),
(236, '화장대', 70000, '2024-09-06', 'South Korea', 'VanityWorks', '1 unit', NULL),
(237, '책장', 55000, '2024-09-07', 'South Korea', 'BookShelfCo', '5 shelves', NULL),
(238, '장난감 정리함', 18000, '2024-09-08', 'South Korea', 'ToyTidy', '1 unit', NULL),
(239, '신발장', 50000, '2024-09-09', 'South Korea', 'ShoeRack', '10 slots', NULL),
(240, '문서 보관함', 22000, '2024-09-10', 'South Korea', 'DocumentHolder', '1 unit', NULL);

-- 바디/헤어/구강/면도 데이터
INSERT INTO item(item_id, name, price, manufacture_date, origin, company, size, color)
VALUES
(241, '샴푸', 12000, '2024-09-01', 'South Korea', 'ShampooCo', '500ml', NULL),
(242, '린스', 13000, '2024-09-02', 'South Korea', 'RinseWorks', '500ml', NULL),
(243, '바디워시', 15000, '2024-09-03', 'South Korea', 'BodyWash', '500ml', NULL),
(244, '치약', 6000, '2024-09-04', 'South Korea', 'ToothPasteCo', '200g', NULL),
(245, '면도기', 20000, '2024-09-05', 'South Korea', 'RazorWorks', '1 unit', NULL),
(246, '페이스크림', 25000, '2024-09-06', 'South Korea', 'FaceCreamCo', '50g', NULL),
(247, '데오도란트', 8000, '2024-09-07', 'South Korea', 'DeoCo', '150ml', NULL),
(248, '헤어에센스', 22000, '2024-09-08', 'South Korea', 'HairEssence', '100ml', NULL),
(249, '핸드로션', 10000, '2024-09-09', 'South Korea', 'HandLotionCo', '300ml', NULL),
(250, '면도크림', 7000, '2024-09-10', 'South Korea', 'ShavingCream', '200g', NULL);

-- 가구/조명/인테리어 데이터
INSERT INTO item(item_id, name, price, manufacture_date, origin, company, size, color)
VALUES
(251, '소파', 250000, '2024-09-01', 'South Korea', 'SofaCo', '3 seater', NULL),
(252, '테이블', 150000, '2024-09-02', 'South Korea', 'TableWorks', '120x80 cm', NULL),
(253, '의자', 80000, '2024-09-03', 'South Korea', 'ChairFactory', NULL, NULL),
(254, '조명', 50000, '2024-09-04', 'South Korea', 'LightCo', '1 unit', NULL),
(255, '커튼', 30000, '2024-09-05', 'South Korea', 'CurtainWorks', '2 panels', NULL),
(256, '책상', 180000, '2024-09-06', 'South Korea', 'DeskDepot', '1 unit', NULL),
(257, '화장대 거울', 60000, '2024-09-07', 'South Korea', 'MirrorCo', '1 unit', NULL),
(258, '서랍장', 120000, '2024-09-08', 'South Korea', 'DrawerDepot', '5 drawers', NULL),
(259, '벽걸이 선반', 40000, '2024-09-09', 'South Korea', 'ShelfWorks', '1 unit', NULL),
(260, '화분', 20000, '2024-09-10', 'South Korea', 'PlantCo', '1 unit', NULL);

-- 구기스포츠 데이터
INSERT INTO item(item_id, name, price, manufacture_date, origin, company, size, color)
VALUES
(261, '축구공', 35000, '2024-09-01', 'South Korea', 'SoccerGear', NULL, NULL),
(262, '농구공', 40000, '2024-09-02', 'South Korea', 'BasketBallCo', NULL, NULL),
(263, '배구공', 30000, '2024-09-03', 'South Korea', 'VolleyballWorks', NULL, NULL),
(264, '야구공', 25000, '2024-09-04', 'South Korea', 'BaseballGear', NULL, NULL),
(265, '테니스공', 15000, '2024-09-05', 'South Korea', 'TennisCo', NULL, NULL),
(266, '하키공', 20000, '2024-09-06', 'South Korea', 'HockeyWorks', NULL, NULL),
(267, '배드민턴 셔틀콕', 12000, '2024-09-07', 'South Korea', 'BadmintonCo', NULL, NULL),
(268, '탁구공', 10000, '2024-09-08', 'South Korea', 'TableTennisGear', NULL, NULL),
(269, '골프공', 60000, '2024-09-09', 'South Korea', 'GolfCo', NULL, NULL),
(270, '크리켓공', 35000, '2024-09-10', 'South Korea', 'CricketGear', NULL, NULL);

-- 헬스/요가 데이터
INSERT INTO item(item_id, name, price, manufacture_date, origin, company, size, color)
VALUES
(271, '요가복 레깅스', 35000, '2024-03-25', 'South Korea', 'ABC 요가', NULL, NULL),
(272, '덤벨 3kg', 20000, '2023-12-15', 'South Korea', 'XYZ 헬스', NULL, NULL),
(273, '요가 블록', 12000, '2024-04-02', 'South Korea', 'DEF 요가', NULL, NULL),
(274, '프로틴 바', 30000, '2024-03-15', 'South Korea', 'GHI 영양', NULL, NULL),
(275, '필라테스 링', 15000, '2024-04-05', 'South Korea', 'JKL 피트니스', NULL, NULL),
(276, '요가 매트 가방', 5000, '2024-04-01', 'South Korea', 'ABC 요가', NULL, NULL),
(277, '케틀벨', 25000, '2023-12-20', 'South Korea', 'MNO 헬스', NULL, NULL),
(278, '요가복 상의 (긴팔)', 40000, '2024-03-28', 'South Korea', 'ABC 요가', NULL, NULL),
(279, '폼롤러', 18000, '2024-04-03', 'South Korea', 'PQR 피트니스', NULL, NULL),
(280, '손목 보호대', 10000, '2024-03-18', 'South Korea', 'STU 스포츠', NULL, NULL);

-- 라켓스포츠 데이터
INSERT INTO item(item_id, name, price, manufacture_date, origin, company, size, color)
VALUES
(281, '테니스 라켓', 80000, '2024-09-01', 'South Korea', 'TennisRacketCo', NULL, NULL),
(282, '배드민턴 라켓', 60000, '2024-09-02', 'South Korea', 'BadmintonRacket', NULL, NULL),
(283, '탁구 라켓', 50000, '2024-09-03', 'South Korea', 'TableTennisRacket', NULL, NULL),
(284, '스쿼시 라켓', 70000, '2024-09-04', 'South Korea', 'SquashRacketCo', NULL, NULL),
(285, '라켓용 그립', 12000, '2024-09-05', 'South Korea', 'GripWorks', NULL, NULL),
(286, '테니스 공', 15000, '2024-09-06', 'South Korea', 'TennisBallCo', NULL, NULL),
(287, '배드민턴 셔틀콕', 10000, '2024-09-07', 'South Korea', 'BadmintonShuttle', NULL, NULL),
(288, '탁구 공', 8000, '2024-09-08', 'South Korea', 'TableTennisBall', NULL, NULL),
(289, '스쿼시 공', 20000, '2024-09-09', 'South Korea', 'SquashBallCo', NULL, NULL),
(290, '라켓 가방', 25000, '2024-09-10', 'South Korea', 'RacketBagCo', NULL, NULL);

-- 수영 데이터
INSERT INTO item(item_id, name, price, manufacture_date, origin, company, size, color)
VALUES
(291, '수영복', 40000, '2024-09-01', 'South Korea', 'SwimwearCo', NULL, NULL),
(292, '수경', 15000, '2024-09-02', 'South Korea', 'GogglesCo', NULL, NULL),
(293, '수모', 12000, '2024-09-03', 'South Korea', 'SwimCapCo', NULL, NULL),
(294, '수영장 수건', 10000, '2024-09-04', 'South Korea', 'TowelWorks', NULL, NULL),
(295, '스노클링 장비', 60000, '2024-09-05', 'South Korea', 'SnorkelCo', NULL, NULL),
(296, '수영 보조기구', 20000, '2024-09-06', 'South Korea', 'SwimAidCo', NULL, NULL),
(297, '수영 장갑', 15000, '2024-09-07', 'South Korea', 'SwimGloveCo', NULL, NULL),
(298, '수영 팔찌', 10000, '2024-09-08', 'South Korea', 'SwimBracelet', NULL, NULL),
(299, '수영 신발', 30000, '2024-09-09', 'South Korea', 'SwimShoes', NULL, NULL),
(300, '비치타올', 12000, '2024-09-10', 'South Korea', 'BeachTowelCo', NULL, NULL);

-- 낚시 데이터
INSERT INTO item(item_id, name, price, manufacture_date, origin, company, size, color)
VALUES
(301, '낚시대', 100000, '2024-09-01', 'South Korea', 'FishingRodCo', NULL, NULL),
(302, '낚시 릴', 80000, '2024-09-02', 'South Korea', 'ReelWorks', NULL, NULL),
(303, '낚시 바늘', 15000, '2024-09-03', 'South Korea', 'HookCo', NULL, NULL),
(304, '낚시미끼', 20000, '2024-09-04', 'South Korea', 'BaitWorks', NULL, NULL),
(305, '낚시줄', 30000, '2024-09-05', 'South Korea', 'LineCo', NULL, NULL),
(306, '낚시 가방', 25000, '2024-09-06', 'South Korea', 'FishingBagCo', NULL, NULL),
(307, '낚시 장갑', 10000, '2024-09-07', 'South Korea', 'FishingGloveCo', NULL, NULL),
(308, '낚시 캠프용 의자', 15000, '2024-09-08', 'South Korea', 'CampingChairCo', NULL, NULL),
(309, '낚시용 쿨러', 30000, '2024-09-09', 'South Korea', 'FishingCoolerCo', NULL, NULL),
(310, '낚시용 챙 모자', 20000, '2024-09-10', 'South Korea', 'FishingHatCo', NULL, NULL);

-- 필기구 데이터
INSERT INTO item(item_id, name, price, manufacture_date, origin, company, size, color)
VALUES
(311, '볼펜', 1500, '2024-09-01', 'South Korea', 'PenCo', NULL, NULL),
(312, '연필', 1000, '2024-09-02', 'South Korea', 'PencilWorks', NULL, NULL),
(313, '형광펜', 2000, '2024-09-03', 'South Korea', 'HighlighterCo', NULL, NULL),
(314, '지우개', 800, '2024-09-04', 'South Korea', 'EraserCo', NULL, NULL),
(315, '샤프', 3000, '2024-09-05', 'South Korea', 'SharpenerCo', NULL, NULL),
(316, '크레용', 2500, '2024-09-06', 'South Korea', 'CrayonCo', NULL, NULL),
(317, '마커', 2000, '2024-09-07', 'South Korea', 'MarkerWorks', NULL, NULL),
(318, '속지', 1500, '2024-09-08', 'South Korea', 'RefillCo', NULL, NULL),
(319, '펜 케이스', 5000, '2024-09-09', 'South Korea', 'PenCaseCo', NULL, NULL),
(320, '펜홀더', 1200, '2024-09-10', 'South Korea', 'PenHolderCo', NULL, NULL);

-- 노트/메모지/A4 데이터
INSERT INTO item(item_id, name, price, manufacture_date, origin, company, size, color)
VALUES
(321, 'A4 용지', 5000, '2024-09-01', 'South Korea', 'PaperCo', NULL, NULL),
(322, '노트', 2500, '2024-09-02', 'South Korea', 'NotebookCo', NULL, NULL),
(323, '메모지', 2000, '2024-09-03', 'South Korea', 'MemoPadCo', NULL, NULL),
(324, '수첩', 3000, '2024-09-04', 'South Korea', 'PlannerCo', NULL, NULL),
(325, '일정표', 4000, '2024-09-05', 'South Korea', 'ScheduleCo', NULL, NULL),
(326, '포스트잇', 1500, '2024-09-06', 'South Korea', 'PostItCo', NULL, NULL),
(327, 'A5 용지', 4500, '2024-09-07', 'South Korea', 'A5PaperCo', NULL, NULL),
(328, '다이어리', 6000, '2024-09-08', 'South Korea', 'DiaryCo', NULL, NULL),
(329, '메모장', 2500, '2024-09-09', 'South Korea', 'MemoBookCo', NULL, NULL),
(330, 'B5 노트', 3000, '2024-09-10', 'South Korea', 'B5NotebookCo', NULL, NULL);

-- 유아 데이터
INSERT INTO item(item_id, name, price, manufacture_date, origin, company, size, color)
VALUES
(331, '유아용 장난감', 15000, '2024-09-01', 'South Korea', 'ToyCo', NULL, NULL),
(332, '아기용 옷', 20000, '2024-09-02', 'South Korea', 'BabyClothesCo', NULL, NULL),
(333, '유아용 식탁', 50000, '2024-09-03', 'South Korea', 'BabyFurnitureCo', NULL, NULL),
(334, '유모차', 200000, '2024-09-04', 'South Korea', 'StrollerCo', NULL, NULL),
(335, '유아용 침대', 150000, '2024-09-05', 'South Korea', 'BabyBedCo', NULL, NULL),
(336, '아기책', 12000, '2024-09-06', 'South Korea', 'BabyBookCo', NULL, NULL),
(337, '유아용 기저귀', 8000, '2024-09-07', 'South Korea', 'DiaperCo', NULL, NULL),
(338, '아기 장난감 세트', 30000, '2024-09-08', 'South Korea', 'ToySetCo', NULL, NULL),
(339, '아기용 식기세트', 10000, '2024-09-09', 'South Korea', 'BabyDishSetCo', NULL, NULL),
(340, '아기용 목욕용품', 7000, '2024-09-10', 'South Korea', 'BabyBathCo', NULL, NULL);

-- 소설 데이터
INSERT INTO item(item_id, name, price, manufacture_date, origin, company, size, color)
VALUES
(341, '노르웨이의 숲', 15000, '2024-09-01', 'South Korea', 'Haruki Murakami', NULL, NULL),
(342, '1984', 12000, '2024-09-02', 'South Korea', 'George Orwell', NULL, NULL),
(343, '백년의 고독', 17000, '2024-09-03', 'South Korea', 'Gabriel García Márquez', NULL, NULL),
(344, '죽은 자의 집 청소', 14000, '2024-09-04', 'South Korea', '고미숙', NULL, NULL),
(345, '해리포터와 마법사의 돌', 25000, '2024-09-05', 'South Korea', 'J.K. Rowling', NULL, NULL),
(346, '자바의 신', 16000, '2024-09-06', 'South Korea', '리우웨이', NULL, NULL),
(347, '호밀밭의 파수꾼', 14000, '2024-09-07', 'South Korea', 'J.D. Salinger', NULL, NULL),
(348, '오만과 편견', 13000, '2024-09-08', 'South Korea', 'Jane Austen', NULL, NULL),
(349, '가려진 길', 15000, '2024-09-09', 'South Korea', '미야베 미유키', NULL, NULL),
(350, '안나 카레니나', 17000, '2024-09-10', 'South Korea', 'Leo Tolstoy', NULL, NULL);

-- 경제/경영 데이터
INSERT INTO item(item_id, name, price, manufacture_date, origin, company, size, color)
VALUES
(351, '이코노미스트', 25000, '2024-09-01', 'South Korea', 'The Economist', NULL, NULL),
(352, '부의 인문학', 20000, '2024-09-02', 'South Korea', '피터 드러커', NULL, NULL),
(353, '하버드 상경학', 22000, '2024-09-03', 'South Korea', 'Harvard Business School', NULL, NULL),
(354, '불황의 경제학', 18000, '2024-09-04', 'South Korea', 'Nassim Nicholas Taleb', NULL, NULL),
(355, '회계의 기초', 16000, '2024-09-05', 'South Korea', 'KPMG', NULL, NULL),
(356, '원칙', 27000, '2024-09-06', 'South Korea', 'Ray Dalio', NULL, NULL),
(357, '경영 전략', 23000, '2024-09-07', 'South Korea', 'Michael Porter', NULL, NULL),
(358, '리더십의 원칙', 25000, '2024-09-08', 'South Korea', 'John C. Maxwell', NULL, NULL),
(359, '부의 시나리오', 19000, '2024-09-09', 'South Korea', 'Daniel Kahneman', NULL, NULL),
(360, '디지털 혁신', 24000, '2024-09-10', 'South Korea', 'Clayton Christensen', NULL, NULL);

-- IT 데이터
INSERT INTO item(item_id, name, price, manufacture_date, origin, company, size, color)
VALUES
(361, '파이썬 프로그래밍', 22000, '2024-09-01', 'South Korea', 'Mark Lutz', NULL, NULL),
(362, '자바스크립트 완벽 가이드', 25000, '2024-09-02', 'South Korea', 'David Flanagan', NULL, NULL),
(363, '클라우드 컴퓨팅', 30000, '2024-09-03', 'South Korea', 'Daniel C. Koper', NULL, NULL),
(364, '소프트웨어 공학', 28000, '2024-09-04', 'South Korea', 'Ian Sommerville', NULL, NULL),
(365, '리눅스 커널', 32000, '2024-09-05', 'South Korea', 'Robert Love', NULL, NULL),
(366, '빅데이터 분석', 35000, '2024-09-06', 'South Korea', 'Bill Franks', NULL, NULL),
(367, '네트워크 기초', 20000, '2024-09-07', 'South Korea', 'Andrew S. Tanenbaum', NULL, NULL),
(368, '인공지능 기초', 29000, '2024-09-08', 'South Korea', 'Stuart Russell', NULL, NULL),
(369, '데이터베이스 시스템', 26000, '2024-09-09', 'South Korea', 'Abraham Silberschatz', NULL, NULL),
(370, '사물인터넷', 27000, '2024-09-10', 'South Korea', 'Ovidiu Vermesan', NULL, NULL);

-- 예술 데이터
INSERT INTO item(item_id, name, price, manufacture_date, origin, company, size, color)
VALUES
(371, '모네의 정원', 30000, '2024-09-01', 'South Korea', 'Claude Monet', NULL, NULL),
(372, '피카소의 작품', 35000, '2024-09-02', 'South Korea', 'Pablo Picasso', NULL, NULL),
(373, '로댕의 조각', 28000, '2024-09-03', 'South Korea', 'Auguste Rodin', NULL, NULL),
(374, '레오나르도 다빈치', 32000, '2024-09-04', 'South Korea', 'Leonardo da Vinci', NULL, NULL),
(375, '현대 미술', 25000, '2024-09-05', 'South Korea', 'Various Artists', NULL, NULL),
(376, '추상 미술', 29000, '2024-09-06', 'South Korea', 'Abstract Artists', NULL, NULL),
(377, '상징주의', 27000, '2024-09-07', 'South Korea', 'Symbolist Artists', NULL, NULL),
(378, '인상파', 26000, '2024-09-08', 'South Korea', 'Impressionist Artists', NULL, NULL),
(379, '큐비즘', 31000, '2024-09-09', 'South Korea', 'Cubist Artists', NULL, NULL),
(380, '바로크 미술', 34000, '2024-09-10', 'South Korea', 'Baroque Artists', NULL, NULL);

-- 기술/공학 데이터
INSERT INTO item(item_id, name, price, manufacture_date, origin, company, size, color)
VALUES
(381, '기계공학 기초', 28000, '2024-09-01', 'South Korea', 'John Bird', NULL, NULL),
(382, '전자공학', 30000, '2024-09-02', 'South Korea', 'David K. Cheng', NULL, NULL),
(383, '화학공학 원론', 32000, '2024-09-03', 'South Korea', 'J. M. Smith', NULL, NULL),
(384, '제어 시스템', 35000, '2024-09-04', 'South Korea', 'Ogata', NULL, NULL),
(385, '재료공학', 31000, '2024-09-05', 'South Korea', 'William D. Callister', NULL, NULL),
(386, '토목공학', 34000, '2024-09-06', 'South Korea', 'R. D. Mays', NULL, NULL),
(387, '열역학', 33000, '2024-09-07', 'South Korea', 'Yaw', NULL, NULL),
(388, '구조역학', 32000, '2024-09-08', 'South Korea', 'Hibbeler', NULL, NULL),
(389, '소프트웨어 공학', 30000, '2024-09-09', 'South Korea', 'Ian Sommerville', NULL, NULL),
(390, '기술적 문제 해결', 31000, '2024-09-10', 'South Korea', 'Andrew S. Tanenbaum', NULL, NULL);


/*
 * 카테고리_상품 테이블 데이터 추가
 */
INSERT INTO category_item(item_id, category_id)
VALUES
(1,9), (2,9), (3,9), (4,9), (5,9), (6,9), (7,9), (8,9), (9,9), (10,9), 
(11,10), (12,10), (13,10), (14,10), (15,10), (16,10), (17,10), (18,10), (19,10), (20,10), 
(21,11), (22,11), (23,11), (24,11), (25,11), (26,11), (27,11), (28,11), (29,11), (30,11), 
(31,12), (32,12), (33,12), (34,12), (35,12), (36,12), (37,12), (38,12), (39,12), (40,12), 
(41,13), (42,13), (43,13), (44,13), (45,13), (46,13), (47,13), (48,13), (49,13), (50,13), 
(51,14), (52,14), (53,14), (54,14), (55,14), (56,14), (57,14), (58,14), (59,14), (60,14), 
(61,15), (62,15), (63,15), (64,15), (65,15), (66,15), (67,15), (68,15), (69,15), (70,15), 
(71,16), (72,16), (73,16), (74,16), (75,16), (76,16), (77,16), (78,16), (79,16), (80,16), 
(81,17), (82,17), (83,17), (84,17), (85,17), (86,17), (87,17), (88,17), (89,17), (90,17), 
(91,18), (92,18), (93,18), (94,18), (95,18), (96,18), (97,18), (98,18), (99,18), (100,18), 
(101,19), (102,19), (103,19), (104,19), (105,19), (106,19), (107,19), (108,19), (109,19), (110,19), 
(111,20), (112,20), (113,20), (114,20), (115,20), (116,20), (117,20), (118,20), (119,20), (120,20), 
(121,21), (122,21), (123,21), (124,21), (125,21), (126,21), (127,21), (128,21), (129,21), (130,21), 
(131,22), (132,22), (133,22), (134,22), (135,22), (136,22), (137,22), (138,22), (139,22), (140,22), 
(141,23), (142,23), (143,23), (144,23), (145,23), (146,23), (147,23), (148,23), (149,23), (150,23), 
(151,24), (152,24), (153,24), (154,24), (155,24), (156,24), (157,24), (158,24), (159,24), (160,24), 
(161,25), (162,25), (163,25), (164,25), (165,25), (166,25), (167,25), (168,25), (169,25), (170,25), 
(171,26), (172,26), (173,26), (174,26), (175,26), (176,26), (177,26), (178,26), (179,26), (180,26), 
(181,27), (182,27), (183,27), (184,27), (185,27), (186,27), (187,27), (188,27), (189,27), (190,27), 
(191,28), (192,28), (193,28), (194,28), (195,28), (196,28), (197,28), (198,28), (199,28), (200,28), 
(201,29), (202,29), (203,29), (204,29), (205,29), (206,29), (207,29), (208,29), (209,29), (210,29), 
(211,30), (212,30), (213,30), (214,30), (215,30), (216,30), (217,30), (218,30), (219,30), (220,30), 
(221,31), (222,31), (223,31), (224,31), (225,31), (226,31), (227,31), (228,31), (229,31), (230,31), 
(231,32), (232,32), (233,32), (234,32), (235,32), (236,32), (237,32), (238,32), (239,32), (240,32), 
(241,33), (242,33), (243,33), (244,33), (245,33), (246,33), (247,33), (248,33), (249,33), (250,33), 
(251,34), (252,34), (253,34), (254,34), (255,34), (256,34), (257,34), (258,34), (259,34), (260,34), 
(261,35), (262,35), (263,35), (264,35), (265,35), (266,35), (267,35), (268,35), (269,35), (270,35), 
(271,36), (272,36), (273,36), (274,36), (275,36), (276,36), (277,36), (278,36), (279,36), (280,36), 
(281,37), (282,37), (283,37), (284,37), (285,37), (286,37), (287,37), (288,37), (289,37), (290,37), 
(291,38), (292,38), (293,38), (294,38), (295,38), (296,38), (297,38), (298,38), (299,38), (300,38), 
(301,39), (302,39), (303,39), (304,39), (305,39), (306,39), (307,39), (308,39), (309,39), (310,39), 
(311,40), (312,40), (313,40), (314,40), (315,40), (316,40), (317,40), (318,40), (319,40), (320,40), 
(321,41), (322,41), (323,41), (324,41), (325,41), (326,41), (327,41), (328,41), (329,41), (330,41), 
(331,42), (332,42), (333,42), (334,42), (335,42), (336,42), (337,42), (338,42), (339,42), (340,42), 
(341,43), (342,43), (343,43), (344,43), (345,43), (346,43), (347,43), (348,43), (349,43), (350,43), 
(351,44), (352,44), (353,44), (354,44), (355,44), (356,44), (357,44), (358,44), (359,44), (360,44), 
(361,45), (362,45), (363,45), (364,45), (365,45), (366,45), (367,45), (368,45), (369,45), (370,45), 
(371,46), (372,46), (373,46), (374,46), (375,46), (376,46), (377,46), (378,46), (379,46), (380,46), 
(381,47), (382,47), (383,47), (384,47), (385,47), (386,47), (387,47), (388,47), (389,47), (390,47);

/*
 * 리뷰 테이블 데이터 추가
 */
 -- 리뷰 테이블 데이터
INSERT INTO review (review_id, stars, contents, date, member_id, item_id) VALUES
(1, 5, 'Excellent product!', '2024-09-10', 3, 25),
(2, 4, 'Very good, but could be improved.', '2024-09-10', 7, 42),
(3, 3, 'Average experience.', '2024-09-10', 15, 30),
(4, 2, 'Not as expected.', '2024-09-10', 3, 58),
(5, 1, 'Terrible, would not recommend.', '2024-09-10', 25, 12),
(6, 5, 'Fantastic! Exceeded expectations.', '2024-09-10', 7, 75),
(7, 4, 'Good, but delivery was late.', '2024-09-10', 15, 30),
(8, 3, 'It’s okay.', '2024-09-10', 3, 85),
(9, 2, 'Disappointing.', '2024-09-10', 20, 45),
(10, 1, 'Horrible quality.', '2024-09-10', 25, 12),
(11, 4, 'Good value for the price.', '2024-09-10', 9, 88),
(12, 5, 'Highly recommend this item!', '2024-09-10', 22, 101),
(13, 3, 'It’s alright, but could be better.', '2024-09-10', 35, 112),
(14, 2, 'Not worth the money.', '2024-09-10', 7, 58),
(15, 1, 'Very disappointed with this purchase.', '2024-09-10', 18, 130),
(16, 4, 'Pretty good overall.', '2024-09-10', 12, 143),
(17, 5, 'Exceeded my expectations!', '2024-09-10', 25, 159),
(18, 3, 'Average, but could use some improvements.', '2024-09-10', 14, 176),
(19, 2, 'Not as described, but it’s okay.', '2024-09-10', 29, 194),
(20, 1, 'Terrible experience. Will not buy again.', '2024-09-10', 35, 115),
(21, 4, 'Decent quality for the price.', '2024-09-10', 6, 82),
(22, 5, 'Amazing! Will buy again.', '2024-09-10', 14, 95),
(23, 3, 'It’s okay, but expected more.', '2024-09-10', 21, 67),
(24, 2, 'Not great, but it works.', '2024-09-10', 28, 140),
(25, 1, 'Very poor quality.', '2024-09-10', 36, 152),
(26, 4, 'Quite good, considering the price.', '2024-09-10', 10, 178),
(27, 5, 'Fantastic purchase. Exceeded expectations.', '2024-09-10', 42, 189),
(28, 3, 'Average item. Nothing special.', '2024-09-10', 7, 210),
(29, 2, 'Not worth the hype.', '2024-09-10', 15, 221),
(30, 1, 'Extremely disappointed.', '2024-09-10', 27, 145),
(31, 4, 'Good product, but delivery was slow.', '2024-09-10', 11, 123),
(32, 5, 'Excellent quality! Highly recommend.', '2024-09-10', 19, 145),
(33, 3, 'It’s acceptable, but nothing special.', '2024-09-10', 24, 160),
(34, 2, 'The item didn’t meet my expectations.', '2024-09-10', 8, 175),
(35, 1, 'Very disappointed. Poor quality.', '2024-09-10', 33, 190),
(36, 4, 'Quite satisfied with the purchase.', '2024-09-10', 12, 202),
(37, 5, 'Absolutely love it! Will buy again.', '2024-09-10', 20, 220),
(38, 3, 'Average. Nothing to write home about.', '2024-09-10', 6, 240),
(39, 2, 'Not as good as I hoped.', '2024-09-10', 31, 255),
(40, 1, 'Terrible experience. Not recommended.', '2024-09-10', 22, 270),
(41, 4, 'Nice quality and fast shipping.', '2024-09-10', 5, 284),
(42, 5, 'Exceeded my expectations! Great buy.', '2024-09-10', 26, 297),
(43, 3, 'It’s okay, but I’ve had better.', '2024-09-10', 11, 310),
(44, 2, 'Not very impressed with the product.', '2024-09-10', 39, 328),
(45, 1, 'Extremely disappointed. Would not recommend.', '2024-09-10', 14, 345),
(46, 4, 'Good product, fair price.', '2024-09-10', 23, 360),
(47, 5, 'Fantastic! Will definitely purchase again.', '2024-09-10', 8, 378),
(48, 3, 'It’s fine, but not exceptional.', '2024-09-10', 30, 370),
(49, 2, 'Not what I expected. Could be better.', '2024-09-10', 21, 355),
(50, 1, 'Very poor quality and service.', '2024-09-10', 43, 340),
(51, 4, 'Good quality, but a bit overpriced.', '2024-09-10', 12, 293),
(52, 5, 'Amazing product! Worth every penny.', '2024-09-10', 7, 308),
(53, 3, 'Average. It does the job.', '2024-09-10', 18, 325),
(54, 2, 'Not great. The quality could be better.', '2024-09-10', 29, 340),
(55, 1, 'Very disappointed. The item is defective.', '2024-09-10', 40, 355),
(56, 4, 'Pretty good for the price.', '2024-09-10', 6, 368),
(57, 5, 'Excellent quality. I’m very happy with it.', '2024-09-10', 22, 375),
(58, 3, 'It’s okay, but I wouldn’t buy it again.', '2024-09-10', 35, 370),
(59, 2, 'Not as described. A bit of a letdown.', '2024-09-10', 13, 360),
(60, 1, 'Terrible experience. Will not purchase again.', '2024-09-10', 48, 345),
(61, 4, 'Solid performance and good build quality.', '2024-09-10', 10, 292),
(62, 5, 'Fantastic item! Exceeds expectations.', '2024-09-10', 8, 310),
(63, 3, 'It’s alright, does the job.', '2024-09-10', 14, 328),
(64, 2, 'Quality is lacking, but it works.', '2024-09-10', 25, 340),
(65, 1, 'Very poor quality. Would not recommend.', '2024-09-10', 37, 357),
(66, 4, 'Good value for money.', '2024-09-10', 12, 368),
(67, 5, 'Highly satisfied with this purchase!', '2024-09-10', 20, 375),
(68, 3, 'Average item, nothing extraordinary.', '2024-09-10', 28, 382),
(69, 2, 'Not great. Could be improved.', '2024-09-10', 32, 345),
(70, 1, 'Very disappointed. Poor quality.', '2024-09-10', 46, 338),
(71, 4, 'Nice product, works well.', '2024-09-10', 9, 320),
(72, 5, 'Absolutely amazing! Will buy again.', '2024-09-10', 5, 312),
(73, 3, 'It’s okay, but there are better options.', '2024-09-10', 15, 325),
(74, 2, 'The item is not as described.', '2024-09-10', 29, 340),
(75, 1, 'Terrible quality. Do not buy.', '2024-09-10', 44, 355),
(76, 4, 'Good quality for the price.', '2024-09-10', 11, 367),
(77, 5, 'Fantastic quality. Very happy with it.', '2024-09-10', 19, 374),
(78, 3, 'It’s acceptable, but could be better.', '2024-09-10', 22, 380),
(79, 2, 'Disappointing. The quality is subpar.', '2024-09-10', 34, 365),
(80, 1, 'Very bad experience. Will not purchase again.', '2024-09-10', 50, 340),
(81, 4, 'Satisfactory performance and build.', '2024-09-10', 7, 290),
(82, 5, 'Wonderful product. Exceeded my expectations.', '2024-09-10', 13, 305),
(83, 3, 'It’s average. Nothing remarkable.', '2024-09-10', 21, 315),
(84, 2, 'Quality could be better. Mediocre at best.', '2024-09-10', 30, 340),
(85, 1, 'Extremely poor quality. Do not recommend.', '2024-09-10', 41, 355),
(86, 4, 'Good purchase. Meets expectations.', '2024-09-10', 16, 368),
(87, 5, 'Outstanding product! Will purchase again.', '2024-09-10', 23, 372),
(88, 3, 'Okay product, but not exceptional.', '2024-09-10', 26, 355),
(89, 2, 'Not what I was hoping for.', '2024-09-10', 35, 360),
(90, 1, 'Very dissatisfied. Poor quality and service.', '2024-09-10', 45, 350),
(91, 4, 'Good item for the price.', '2024-09-10', 8, 285),
(92, 5, 'Excellent quality. Very happy with it.', '2024-09-10', 17, 307),
(93, 3, 'It’s okay, but could use improvements.', '2024-09-10', 26, 318),
(94, 2, 'Not as good as expected.', '2024-09-10', 33, 340),
(95, 1, 'Poor quality. Not recommended.', '2024-09-10', 39, 355),
(96, 4, 'Satisfactory product. Worth the money.', '2024-09-10', 12, 370),
(97, 5, 'Amazing item! Exceeds all expectations.', '2024-09-10', 24, 375),
(98, 3, 'Average quality. Meets basic needs.', '2024-09-10', 31, 380),
(99, 2, 'Disappointing. Could be much better.', '2024-09-10', 40, 365),
(100, 1, 'Terrible. Will never buy this again.', '2024-09-10', 48, 355),
(101, 4, 'Good item, but the packaging was poor.', '2024-09-10', 5, 143),
(102, 5, 'Excellent quality! Worth every penny.', '2024-09-10', 8, 121),
(103, 3, 'It’s okay, but not as good as expected.', '2024-09-10', 15, 110),
(104, 2, 'Not very happy with the quality.', '2024-09-10', 20, 130),
(105, 1, 'Very disappointed. The item was defective.', '2024-09-10', 22, 142),
(106, 4, 'Decent quality and fast shipping.', '2024-09-10', 12, 125),
(107, 5, 'Fantastic product! I’m very pleased.', '2024-09-10', 18, 136),
(108, 3, 'Average. It works, but could be better.', '2024-09-10', 27, 148),
(109, 2, 'Not as described. Quite disappointing.', '2024-09-10', 35, 119),
(110, 1, 'Very poor quality. Will not buy again.', '2024-09-10', 30, 133),
(111, 4, 'Good quality, though a bit pricey.', '2024-09-10', 6, 112),
(112, 5, 'Excellent product. Very satisfied!', '2024-09-10', 12, 145),
(113, 3, 'It’s okay, but not outstanding.', '2024-09-10', 20, 128),
(114, 2, 'Quality is not as expected.', '2024-09-10', 25, 135),
(115, 1, 'Very disappointing. Defective item.', '2024-09-10', 28, 142),
(116, 4, 'Good value for the price.', '2024-09-10', 8, 139),
(117, 5, 'Fantastic quality! Highly recommend.', '2024-09-10', 15, 123),
(118, 3, 'Average. Meets basic needs.', '2024-09-10', 22, 146),
(119, 2, 'Not great. Quality could be better.', '2024-09-10', 35, 131),
(120, 1, 'Very poor quality. Not worth it.', '2024-09-10', 40, 138),
(121, 4, 'Pretty good product, but shipping was slow.', '2024-09-10', 11, 121),
(122, 5, 'Amazing! Exceeded my expectations.', '2024-09-10', 7, 116),
(123, 3, 'It’s fine, but could be improved.', '2024-09-10', 19, 129),
(124, 2, 'Quality is subpar. Disappointed.', '2024-09-10', 26, 141),
(125, 1, 'Terrible quality. Do not buy.', '2024-09-10', 33, 120),
(126, 4, 'Good purchase. Worth the price.', '2024-09-10', 9, 130),
(127, 5, 'Excellent item. Very happy with it.', '2024-09-10', 17, 148),
(128, 3, 'Average. Does the job but nothing special.', '2024-09-10', 24, 133),
(129, 2, 'Not as expected. Quality is lacking.', '2024-09-10', 30, 139),
(130, 1, 'Very disappointed. Poorly made.', '2024-09-10', 39, 125),
(131, 4, 'Satisfactory quality. Good for the price.', '2024-09-10', 10, 127),
(132, 5, 'Wonderful product. Will purchase again.', '2024-09-10', 14, 147),
(133, 3, 'It’s okay, but I’ve had better.', '2024-09-10', 18, 140),
(134, 2, 'Quality is below expectations.', '2024-09-10', 23, 126),
(135, 1, 'Very poor experience. Would not recommend.', '2024-09-10', 31, 134),
(136, 4, 'Good item. Worth the money.', '2024-09-10', 13, 141),
(137, 5, 'Fantastic quality! I’m very pleased.', '2024-09-10', 21, 118),
(138, 3, 'Average product. It works.', '2024-09-10', 29, 149),
(139, 2, 'Disappointing. Quality is not great.', '2024-09-10', 36, 122),
(140, 1, 'Extremely disappointed. The item was defective.', '2024-09-10', 44, 137),
(141, 4, 'Good quality, although the delivery was delayed.', '2024-09-10', 7, 132),
(142, 5, 'Excellent product! Worth every penny.', '2024-09-10', 12, 114),
(143, 3, 'It’s okay, but not exceptional.', '2024-09-10', 18, 121),
(144, 2, 'Quality was not as expected.', '2024-09-10', 24, 145),
(145, 1, 'Very disappointed. Item arrived damaged.', '2024-09-10', 29, 138),
(146, 4, 'Good for the price, but could be improved.', '2024-09-10', 9, 117),
(147, 5, 'Fantastic quality! Highly recommend.', '2024-09-10', 21, 123),
(148, 3, 'Average. It works, but there are better options.', '2024-09-10', 26, 130),
(149, 2, 'Not as described. The quality is lacking.', '2024-09-10', 32, 140),
(150, 1, 'Extremely poor quality. Do not buy.', '2024-09-10', 37, 146),
(151, 4, 'Solid item with good features.', '2024-09-10', 10, 119),
(152, 5, 'Amazing! Exceeded my expectations.', '2024-09-10', 14, 134),
(153, 3, 'It’s fine, but not impressive.', '2024-09-10', 22, 141),
(154, 2, 'Quality is below expectations.', '2024-09-10', 27, 122),
(155, 1, 'Very disappointed with the product.', '2024-09-10', 34, 133),
(156, 4, 'Good quality, though the shipping was slow.', '2024-09-10', 13, 127),
(157, 5, 'Fantastic item. I’m very happy with it.', '2024-09-10', 19, 115),
(158, 3, 'It’s average. Nothing special.', '2024-09-10', 25, 139),
(159, 2, 'Not worth the price. Quality is lacking.', '2024-09-10', 30, 132),
(160, 1, 'Terrible experience. Will not buy again.', '2024-09-10', 43, 120),
(161, 4, 'Good quality and value for the price.', '2024-09-10', 11, 121),
(162, 5, 'Wonderful product. Highly recommend.', '2024-09-10', 17, 116),
(163, 3, 'Okay product. Meets basic needs.', '2024-09-10', 23, 128),
(164, 2, 'Disappointing. The quality is subpar.', '2024-09-10', 31, 134),
(165, 1, 'Very poor quality. Would not recommend.', '2024-09-10', 38, 140),
(166, 4, 'Satisfactory. Good for the price.', '2024-09-10', 12, 133),
(167, 5, 'Fantastic! I’m very pleased with the purchase.', '2024-09-10', 26, 117),
(168, 3, 'Average. Could be better.', '2024-09-10', 33, 129),
(169, 2, 'Not as expected. Quality could be improved.', '2024-09-10', 40, 122),
(170, 1, 'Extremely disappointed. Defective item.', '2024-09-10', 46, 138),
(171, 4, 'Good value. Satisfied with the purchase.', '2024-09-10', 15, 125),
(172, 5, 'Excellent product. Exceeds expectations.', '2024-09-10', 22, 145),
(173, 3, 'It’s okay. Does the job.', '2024-09-10', 29, 137),
(174, 2, 'Quality is not great. Disappointed.', '2024-09-10', 35, 139),
(175, 1, 'Very poor quality. Will not buy again.', '2024-09-10', 41, 121),
(176, 4, 'Solid product. Worth the money.', '2024-09-10', 14, 116),
(177, 5, 'Fantastic quality! I’m very happy.', '2024-09-10', 20, 128),
(178, 3, 'Average. It works but nothing exceptional.', '2024-09-10', 26, 145),
(179, 2, 'Not as described. Quality could be better.', '2024-09-10', 33, 130),
(180, 1, 'Terrible quality. Very disappointed.', '2024-09-10', 44, 135),
(181, 4, 'Good product with decent features.', '2024-09-10', 9, 121),
(182, 5, 'Amazing quality! Exceeded my expectations.', '2024-09-10', 16, 114),
(183, 3, 'It’s fine. Could be improved.', '2024-09-10', 24, 127),
(184, 2, 'Disappointing quality. Not worth it.', '2024-09-10', 31, 140),
(185, 1, 'Extremely poor quality. Would not recommend.', '2024-09-10', 38, 133),
(186, 4, 'Good quality for the price.', '2024-09-10', 13, 137),
(187, 5, 'Fantastic! Will definitely buy again.', '2024-09-10', 19, 119),
(188, 3, 'Average product. It meets basic needs.', '2024-09-10', 27, 143),
(189, 2, 'Quality is lacking. Not impressed.', '2024-09-10', 34, 126),
(190, 1, 'Very poor quality. Do not buy.', '2024-09-10', 42, 130),
(191, 4, 'Good value. Satisfied with the purchase.', '2024-09-10', 11, 121),
(192, 5, 'Excellent product. Exceeds expectations.', '2024-09-10', 18, 147),
(193, 3, 'Okay, but could use some improvements.', '2024-09-10', 26, 135),
(194, 2, 'Disappointing. The quality is below par.', '2024-09-10', 33, 129),
(195, 1, 'Very bad quality. Will not buy again.', '2024-09-10', 40, 132),
(196, 4, 'Good item. Worth the money.', '2024-09-10', 14, 143),
(197, 5, 'Fantastic! Very happy with it.', '2024-09-10', 21, 115),
(198, 3, 'It’s average. Meets basic expectations.', '2024-09-10', 28, 126),
(199, 2, 'Not as expected. Quality could be better.', '2024-09-10', 35, 134),
(200, 1, 'Very disappointed. The product is defective.', '2024-09-10', 42, 138),
(201, 4, 'Good quality item. Satisfied with the purchase.', '2024-09-10', 6, 145),
(202, 5, 'Excellent! Exceeded my expectations.', '2024-09-10', 13, 123),
(203, 3, 'Average. Works fine, but could be improved.', '2024-09-10', 21, 130),
(204, 2, 'Not as good as I hoped. Quality is lacking.', '2024-09-10', 27, 142),
(205, 1, 'Very disappointed. The item was defective.', '2024-09-10', 34, 138),
(206, 4, 'Good value for the price.', '2024-09-10', 10, 117),
(207, 5, 'Fantastic product. Very pleased.', '2024-09-10', 18, 116),
(208, 3, 'Okay product, but nothing special.', '2024-09-10', 23, 121),
(209, 2, 'Disappointing. Quality is subpar.', '2024-09-10', 31, 132),
(210, 1, 'Very poor quality. Will not purchase again.', '2024-09-10', 41, 129),
(211, 4, 'Solid item. Worth the money.', '2024-09-10', 7, 146),
(212, 5, 'Amazing! Exceeded all expectations.', '2024-09-10', 15, 125),
(213, 3, 'It’s average. Meets basic needs.', '2024-09-10', 22, 124),
(214, 2, 'Quality is not great. Quite disappointed.', '2024-09-10', 30, 144),
(215, 1, 'Terrible quality. Do not buy.', '2024-09-10', 36, 135),
(216, 4, 'Good product for the price.', '2024-09-10', 11, 131),
(217, 5, 'Fantastic quality. Highly recommend!', '2024-09-10', 19, 140),
(218, 3, 'It’s okay, but could be better.', '2024-09-10', 28, 142),
(219, 2, 'Not as expected. Quality needs improvement.', '2024-09-10', 32, 143),
(220, 1, 'Very disappointed. Poor quality.', '2024-09-10', 45, 139),
(221, 4, 'Good quality item. Satisfied with the purchase.', '2024-09-10', 14, 122),
(222, 5, 'Excellent product! I’m very pleased.', '2024-09-10', 23, 115),
(223, 3, 'Average. Does the job but nothing special.', '2024-09-10', 29, 127),
(224, 2, 'Not worth the money. Quality is lacking.', '2024-09-10', 35, 128),
(225, 1, 'Terrible. Will not buy again.', '2024-09-10', 40, 137),
(226, 4, 'Good for the price. Satisfied.', '2024-09-10', 8, 141),
(227, 5, 'Fantastic quality. Exceeds expectations.', '2024-09-10', 16, 126),
(228, 3, 'It’s fine. Could be improved.', '2024-09-10', 25, 132),
(229, 2, 'Disappointing quality. Not as expected.', '2024-09-10', 31, 130),
(230, 1, 'Very poor quality. Do not recommend.', '2024-09-10', 38, 145),
(231, 4, 'Good value. Meets expectations.', '2024-09-10', 12, 123),
(232, 5, 'Wonderful product. Very happy with it.', '2024-09-10', 19, 116),
(233, 3, 'Average. It does the job but nothing more.', '2024-09-10', 27, 124),
(234, 2, 'Quality is subpar. Very disappointing.', '2024-09-10', 32, 141),
(235, 1, 'Extremely poor quality. Will not buy again.', '2024-09-10', 44, 137),
(236, 4, 'Good product. Worth the money.', '2024-09-10', 15, 122),
(237, 5, 'Fantastic quality. Exceeds my expectations.', '2024-09-10', 22, 127),
(238, 3, 'It’s okay. Meets basic requirements.', '2024-09-10', 30, 133),
(239, 2, 'Disappointing. Quality could be much better.', '2024-09-10', 37, 142),
(240, 1, 'Very disappointed. Defective item.', '2024-09-10', 46, 134),
(241, 4, 'Good quality for the price.', '2024-09-10', 13, 120),
(242, 5, 'Wonderful! Exceeded my expectations.', '2024-09-10', 20, 115),
(243, 3, 'Average. It works but could be better.', '2024-09-10', 26, 131),
(244, 2, 'Quality is lacking. Not worth it.', '2024-09-10', 33, 139),
(245, 1, 'Very poor quality. Do not recommend.', '2024-09-10', 41, 126),
(246, 4, 'Good value and decent quality.', '2024-09-10', 16, 125),
(247, 5, 'Fantastic quality. I’m very pleased.', '2024-09-10', 24, 140),
(248, 3, 'It’s fine but not exceptional.', '2024-09-10', 32, 138),
(249, 2, 'Disappointing. Quality could be much better.', '2024-09-10', 39, 133),
(250, 1, 'Terrible. Will not buy again.', '2024-09-10', 47, 145),
(251, 4, 'Good quality item. Satisfied with the purchase.', '2024-09-10', 7, 85),
(252, 5, 'Excellent! Exceeded my expectations.', '2024-09-10', 12, 91),
(253, 3, 'Average. Works fine, but could be improved.', '2024-09-10', 18, 77),
(254, 2, 'Not as good as I hoped. Quality is lacking.', '2024-09-10', 25, 68),
(255, 1, 'Very disappointed. The item was defective.', '2024-09-10', 31, 93),
(256, 4, 'Good value for the price.', '2024-09-10', 10, 89),
(257, 5, 'Fantastic product. Very pleased.', '2024-09-10', 19, 94),
(258, 3, 'Okay product, but nothing special.', '2024-09-10', 22, 81),
(259, 2, 'Disappointing. Quality is subpar.', '2024-09-10', 30, 99),
(260, 1, 'Very poor quality. Will not purchase again.', '2024-09-10', 37, 76),
(261, 4, 'Solid item. Worth the money.', '2024-09-10', 6, 83),
(262, 5, 'Amazing! Exceeded all expectations.', '2024-09-10', 14, 92),
(263, 3, 'It’s average. Meets basic needs.', '2024-09-10', 20, 87),
(264, 2, 'Quality is not great. Quite disappointed.', '2024-09-10', 28, 78),
(265, 1, 'Terrible quality. Do not buy.', '2024-09-10', 35, 98),
(266, 4, 'Good product for the price.', '2024-09-10', 8, 96),
(267, 5, 'Fantastic quality. Highly recommend!', '2024-09-10', 16, 74),
(268, 3, 'It’s okay, but could be better.', '2024-09-10', 26, 90),
(269, 2, 'Not as expected. Quality needs improvement.', '2024-09-10', 33, 82),
(270, 1, 'Very disappointed. Poor quality.', '2024-09-10', 41, 86),
(271, 4, 'Good quality item. Satisfied with the purchase.', '2024-09-10', 11, 97),
(272, 5, 'Excellent product! I’m very pleased.', '2024-09-10', 18, 84),
(273, 3, 'Average. Does the job but nothing special.', '2024-09-10', 29, 75),
(274, 2, 'Quality is lacking. Very disappointing.', '2024-09-10', 36, 79),
(275, 1, 'Terrible. Will not buy again.', '2024-09-10', 43, 95),
(276, 4, 'Good for the price. Satisfied.', '2024-09-10', 15, 89),
(277, 5, 'Fantastic quality. Exceeds expectations.', '2024-09-10', 23, 93),
(278, 3, 'It’s fine but not exceptional.', '2024-09-10', 30, 77),
(279, 2, 'Disappointing. Quality could be much better.', '2024-09-10', 37, 91),
(280, 1, 'Very poor quality. Do not recommend.', '2024-09-10', 44, 98),
(281, 4, 'Good product with decent features.', '2024-09-10', 12, 85),
(282, 5, 'Wonderful product. Very happy with it.', '2024-09-10', 20, 88),
(283, 3, 'It’s okay. Meets basic needs.', '2024-09-10', 27, 76),
(284, 2, 'Quality is subpar. Not worth it.', '2024-09-10', 34, 79),
(285, 1, 'Very poor quality. Will not buy again.', '2024-09-10', 42, 93),
(286, 4, 'Good quality for the price.', '2024-09-10', 15, 87),
(287, 5, 'Fantastic! I’m very pleased with it.', '2024-09-10', 22, 94),
(288, 3, 'Average. It does the job but could be better.', '2024-09-10', 30, 77),
(289, 2, 'Disappointing. Quality could be better.', '2024-09-10', 37, 78),
(290, 1, 'Terrible quality. Very disappointed.', '2024-09-10', 45, 90),
(291, 4, 'Good value. Satisfied with the purchase.', '2024-09-10', 14, 84),
(292, 5, 'Wonderful! Exceeded my expectations.', '2024-09-10', 20, 86),
(293, 3, 'Average product. It meets basic needs.', '2024-09-10', 27, 81),
(294, 2, 'Quality is lacking. Not worth it.', '2024-09-10', 33, 95),
(295, 1, 'Very disappointed. The product is defective.', '2024-09-10', 41, 98),
(296, 4, 'Good product. Worth the money.', '2024-09-10', 18, 89),
(297, 5, 'Fantastic quality! I’m very happy.', '2024-09-10', 23, 92),
(298, 3, 'It’s fine but could be better.', '2024-09-10', 30, 77),
(299, 2, 'Disappointing. Quality could be much better.', '2024-09-10', 37, 84),
(300, 1, 'Very poor quality. Will not buy again.', '2024-09-10', 44, 97),
(301, 4, 'Good quality item. Satisfied with the purchase.', '2024-09-10', 9, 92),
(302, 5, 'Excellent! Exceeded my expectations.', '2024-09-10', 14, 75),
(303, 3, 'Average. Works fine, but could be improved.', '2024-09-10', 22, 81),
(304, 2, 'Not as good as I hoped. Quality is lacking.', '2024-09-10', 30, 68),
(305, 1, 'Very disappointed. The item was defective.', '2024-09-10', 36, 77),
(306, 4, 'Good value for the price.', '2024-09-10', 11, 84),
(307, 5, 'Fantastic product. Very pleased.', '2024-09-10', 18, 94),
(308, 3, 'Okay product, but nothing special.', '2024-09-10', 25, 78),
(309, 2, 'Disappointing. Quality is subpar.', '2024-09-10', 32, 97),
(310, 1, 'Very poor quality. Will not purchase again.', '2024-09-10', 40, 95),
(311, 4, 'Solid item. Worth the money.', '2024-09-10', 7, 83),
(312, 5, 'Amazing! Exceeded all expectations.', '2024-09-10', 13, 88),
(313, 3, 'It’s average. Meets basic needs.', '2024-09-10', 20, 90),
(314, 2, 'Quality is not great. Quite disappointed.', '2024-09-10', 28, 79),
(315, 1, 'Terrible quality. Do not buy.', '2024-09-10', 34, 96),
(316, 4, 'Good product for the price.', '2024-09-10', 12, 85),
(317, 5, 'Fantastic quality. Highly recommend!', '2024-09-10', 21, 91),
(318, 3, 'It’s okay, but could be better.', '2024-09-10', 26, 88),
(319, 2, 'Not as expected. Quality needs improvement.', '2024-09-10', 33, 82),
(320, 1, 'Very disappointed. Poor quality.', '2024-09-10', 42, 97),
(321, 4, 'Good quality item. Satisfied with the purchase.', '2024-09-10', 15, 86),
(322, 5, 'Excellent product! I’m very pleased.', '2024-09-10', 22, 80),
(323, 3, 'Average. Does the job but nothing special.', '2024-09-10', 29, 74),
(324, 2, 'Quality is lacking. Very disappointing.', '2024-09-10', 35, 89),
(325, 1, 'Terrible. Will not buy again.', '2024-09-10', 43, 93),
(326, 4, 'Good for the price. Satisfied.', '2024-09-10', 17, 77),
(327, 5, 'Fantastic quality. Exceeds expectations.', '2024-09-10', 25, 96),
(328, 3, 'It’s fine but not exceptional.', '2024-09-10', 31, 84),
(329, 2, 'Disappointing. Quality could be much better.', '2024-09-10', 38, 82),
(330, 1, 'Very poor quality. Do not recommend.', '2024-09-10', 46, 97),
(331, 4, 'Good product with decent features.', '2024-09-10', 16, 85),
(332, 5, 'Wonderful product. Very happy with it.', '2024-09-10', 23, 91),
(333, 3, 'It’s okay. Meets basic needs.', '2024-09-10', 30, 75),
(334, 2, 'Quality is subpar. Not worth it.', '2024-09-10', 37, 79),
(335, 1, 'Very poor quality. Will not buy again.', '2024-09-10', 44, 96),
(336, 4, 'Good quality for the price.', '2024-09-10', 18, 82),
(337, 5, 'Fantastic! I’m very pleased with it.', '2024-09-10', 26, 90),
(338, 3, 'Average. It does the job but could be better.', '2024-09-10', 32, 77),
(339, 2, 'Disappointing. Quality could be much better.', '2024-09-10', 39, 84),
(340, 1, 'Very poor quality. Will not buy again.', '2024-09-10', 47, 91),
(341, 4, 'Good value. Satisfied with the purchase.', '2024-09-10', 14, 88),
(342, 5, 'Wonderful! Exceeded my expectations.', '2024-09-10', 21, 95),
(343, 3, 'Average product. It meets basic needs.', '2024-09-10', 28, 77),
(344, 2, 'Quality is lacking. Not worth it.', '2024-09-10', 34, 82),
(345, 1, 'Very disappointed. The product is defective.', '2024-09-10', 41, 96),
(346, 4, 'Good product. Worth the money.', '2024-09-10', 19, 84),
(347, 5, 'Fantastic quality! I’m very happy.', '2024-09-10', 27, 90),
(348, 3, 'It’s fine but could be better.', '2024-09-10', 32, 77),
(349, 2, 'Disappointing. Quality could be much better.', '2024-09-10', 39, 82),
(350, 1, 'Very poor quality. Will not buy again.', '2024-09-10', 46, 95),
(351, 4, 'Good quality item. Satisfied with the purchase.', '2024-09-10', 8, 82),
(352, 5, 'Excellent! Exceeded my expectations.', '2024-09-10', 13, 76),
(353, 3, 'Average. Works fine, but could be improved.', '2024-09-10', 22, 71),
(354, 2, 'Not as good as I hoped. Quality is lacking.', '2024-09-10', 29, 64),
(355, 1, 'Very disappointed. The item was defective.', '2024-09-10', 35, 99),
(356, 4, 'Good value for the price.', '2024-09-10', 11, 87),
(357, 5, 'Fantastic product. Very pleased.', '2024-09-10', 17, 95),
(358, 3, 'Okay product, but nothing special.', '2024-09-10', 23, 82),
(359, 2, 'Disappointing. Quality is subpar.', '2024-09-10', 31, 91),
(360, 1, 'Very poor quality. Will not purchase again.', '2024-09-10', 39, 78),
(361, 4, 'Solid item. Worth the money.', '2024-09-10', 7, 85),
(362, 5, 'Amazing! Exceeded all expectations.', '2024-09-10', 14, 84),
(363, 3, 'It’s average. Meets basic needs.', '2024-09-10', 21, 88),
(364, 2, 'Quality is not great. Quite disappointed.', '2024-09-10', 28, 97),
(365, 1, 'Terrible quality. Do not buy.', '2024-09-10', 34, 90),
(366, 4, 'Good product for the price.', '2024-09-10', 13, 83),
(367, 5, 'Fantastic quality. Highly recommend!', '2024-09-10', 19, 98),
(368, 3, 'It’s okay, but could be better.', '2024-09-10', 26, 81),
(369, 2, 'Not as expected. Quality needs improvement.', '2024-09-10', 33, 96),
(370, 1, 'Very disappointed. Poor quality.', '2024-09-10', 42, 93),
(371, 4, 'Good quality item. Satisfied with the purchase.', '2024-09-10', 12, 99),
(372, 5, 'Excellent product! I’m very pleased.', '2024-09-10', 20, 76),
(373, 3, 'Average. Does the job but nothing special.', '2024-09-10', 29, 75),
(374, 2, 'Quality is lacking. Very disappointing.', '2024-09-10', 35, 86),
(375, 1, 'Terrible. Will not buy again.', '2024-09-10', 43, 77),
(376, 4, 'Good for the price. Satisfied.', '2024-09-10', 16, 93),
(377, 5, 'Fantastic quality. Exceeds expectations.', '2024-09-10', 23, 87),
(378, 3, 'It’s fine but not exceptional.', '2024-09-10', 30, 89),
(379, 2, 'Disappointing. Quality could be much better.', '2024-09-10', 38, 96),
(380, 1, 'Very poor quality. Do not recommend.', '2024-09-10', 47, 91),
(381, 4, 'Good product with decent features.', '2024-09-10', 18, 77),
(382, 5, 'Wonderful product. Very happy with it.', '2024-09-10', 26, 80),
(383, 3, 'It’s okay. Meets basic needs.', '2024-09-10', 32, 84),
(384, 2, 'Quality is subpar. Not worth it.', '2024-09-10', 39, 82),
(385, 1, 'Very poor quality. Will not buy again.', '2024-09-10', 45, 95),
(386, 4, 'Good quality for the price.', '2024-09-10', 15, 79),
(387, 5, 'Fantastic! I’m very pleased with it.', '2024-09-10', 23, 84),
(388, 3, 'Average. It does the job but could be better.', '2024-09-10', 30, 72),
(389, 2, 'Disappointing. Quality could be much better.', '2024-09-10', 38, 91),
(390, 1, 'Very poor quality. Will not buy again.', '2024-09-10', 46, 88),
(391, 4, 'Good value. Satisfied with the purchase.', '2024-09-10', 14, 96),
(392, 5, 'Wonderful! Exceeded my expectations.', '2024-09-10', 21, 92),
(393, 3, 'Average product. It meets basic needs.', '2024-09-10', 28, 77),
(394, 2, 'Quality is lacking. Not worth it.', '2024-09-10', 34, 84),
(395, 1, 'Very disappointed. The product is defective.', '2024-09-10', 41, 95),
(396, 4, 'Good product. Worth the money.', '2024-09-10', 17, 87),
(397, 5, 'Fantastic quality! I’m very happy.', '2024-09-10', 27, 80),
(398, 3, 'It’s fine but could be better.', '2024-09-10', 33, 74),
(399, 2, 'Disappointing. Quality could be much better.', '2024-09-10', 39, 82),
(400, 1, 'Very poor quality. Will not buy again.', '2024-09-10', 46, 90),
(401, 4, 'Good quality item. Satisfied with the purchase.', '2024-09-10', 9, 25),
(402, 5, 'Excellent! Exceeded my expectations.', '2024-09-10', 15, 42),
(403, 3, 'Average. Works fine, but could be improved.', '2024-09-10', 22, 18),
(404, 2, 'Not as good as I hoped. Quality is lacking.', '2024-09-10', 30, 12),
(405, 1, 'Very disappointed. The item was defective.', '2024-09-10', 36, 47),
(406, 4, 'Good value for the price.', '2024-09-10', 11, 28),
(407, 5, 'Fantastic product. Very pleased.', '2024-09-10', 17, 39),
(408, 3, 'Okay product, but nothing special.', '2024-09-10', 23, 46),
(409, 2, 'Disappointing. Quality is subpar.', '2024-09-10', 31, 50),
(410, 1, 'Very poor quality. Will not purchase again.', '2024-09-10', 40, 33),
(411, 4, 'Solid item. Worth the money.', '2024-09-10', 7, 22),
(412, 5, 'Amazing! Exceeded all expectations.', '2024-09-10', 13, 48),
(413, 3, 'It’s average. Meets basic needs.', '2024-09-10', 21, 15),
(414, 2, 'Quality is not great. Quite disappointed.', '2024-09-10', 28, 11),
(415, 1, 'Terrible quality. Do not buy.', '2024-09-10', 34, 45),
(416, 4, 'Good product for the price.', '2024-09-10', 12, 27),
(417, 5, 'Fantastic quality. Highly recommend!', '2024-09-10', 19, 38),
(418, 3, 'It’s okay, but could be better.', '2024-09-10', 26, 19),
(419, 2, 'Not as expected. Quality needs improvement.', '2024-09-10', 33, 44),
(420, 1, 'Very disappointed. Poor quality.', '2024-09-10', 42, 31),
(421, 4, 'Good quality item. Satisfied with the purchase.', '2024-09-10', 15, 50),
(422, 5, 'Excellent product! I’m very pleased.', '2024-09-10', 22, 29),
(423, 3, 'Average. Does the job but nothing special.', '2024-09-10', 29, 10),
(424, 2, 'Quality is lacking. Very disappointing.', '2024-09-10', 35, 40),
(425, 1, 'Terrible. Will not buy again.', '2024-09-10', 43, 23),
(426, 4, 'Good for the price. Satisfied.', '2024-09-10', 16, 45),
(427, 5, 'Fantastic quality. Exceeds expectations.', '2024-09-10', 23, 18),
(428, 3, 'It’s fine but not exceptional.', '2024-09-10', 30, 20),
(429, 2, 'Disappointing. Quality could be much better.', '2024-09-10', 38, 37),
(430, 1, 'Very poor quality. Do not recommend.', '2024-09-10', 47, 44),
(431, 4, 'Good product with decent features.', '2024-09-10', 18, 30),
(432, 5, 'Wonderful product. Very happy with it.', '2024-09-10', 26, 28),
(433, 3, 'It’s okay. Meets basic needs.', '2024-09-10', 32, 21),
(434, 2, 'Quality is subpar. Not worth it.', '2024-09-10', 39, 35),
(435, 1, 'Very poor quality. Will not buy again.', '2024-09-10', 45, 19),
(436, 4, 'Good quality for the price.', '2024-09-10', 14, 27),
(437, 5, 'Fantastic! I’m very pleased with it.', '2024-09-10', 21, 50),
(438, 3, 'Average. It does the job but could be better.', '2024-09-10', 30, 23),
(439, 2, 'Disappointing. Quality could be much better.', '2024-09-10', 38, 30),
(440, 1, 'Very poor quality. Will not buy again.', '2024-09-10', 46, 40),
(441, 4, 'Good value. Satisfied with the purchase.', '2024-09-10', 14, 22),
(442, 5, 'Wonderful! Exceeded my expectations.', '2024-09-10', 21, 37),
(443, 3, 'Average product. It meets basic needs.', '2024-09-10', 28, 48),
(444, 2, 'Quality is lacking. Not worth it.', '2024-09-10', 34, 12),
(445, 1, 'Very disappointed. The product is defective.', '2024-09-10', 41, 26),
(446, 4, 'Good product. Worth the money.', '2024-09-10', 17, 39),
(447, 5, 'Fantastic quality! I’m very happy.', '2024-09-10', 27, 21),
(448, 3, 'It’s fine but could be better.', '2024-09-10', 33, 25),
(449, 2, 'Disappointing. Quality could be much better.', '2024-09-10', 39, 46),
(450, 1, 'Very poor quality. Will not buy again.', '2024-09-10', 46, 50),
(451, 4, 'Good quality item. Satisfied with the purchase.', '2024-09-10', 5, 16),
(452, 5, 'Excellent! Exceeded my expectations.', '2024-09-10', 12, 10),
(453, 3, 'Average. Works fine, but could be improved.', '2024-09-10', 19, 13),
(454, 2, 'Not as good as I hoped. Quality is lacking.', '2024-09-10', 23, 17),
(455, 1, 'Very disappointed. The item was defective.', '2024-09-10', 28, 11),
(456, 4, 'Good value for the price.', '2024-09-10', 6, 14),
(457, 5, 'Fantastic product. Very pleased.', '2024-09-10', 15, 19),
(458, 3, 'Okay product, but nothing special.', '2024-09-10', 22, 12),
(459, 2, 'Disappointing. Quality is subpar.', '2024-09-10', 29, 20),
(460, 1, 'Very poor quality. Will not purchase again.', '2024-09-10', 33, 15),
(461, 4, 'Solid item. Worth the money.', '2024-09-10', 8, 18),
(462, 5, 'Amazing! Exceeded all expectations.', '2024-09-10', 14, 13),
(463, 3, 'It’s average. Meets basic needs.', '2024-09-10', 20, 16),
(464, 2, 'Quality is not great. Quite disappointed.', '2024-09-10', 25, 17),
(465, 1, 'Terrible quality. Do not buy.', '2024-09-10', 30, 14),
(466, 4, 'Good product for the price.', '2024-09-10', 11, 19),
(467, 5, 'Fantastic quality. Highly recommend!', '2024-09-10', 18, 11),
(468, 3, 'It’s okay, but could be better.', '2024-09-10', 27, 12),
(469, 2, 'Not as expected. Quality needs improvement.', '2024-09-10', 34, 16),
(470, 1, 'Very disappointed. Poor quality.', '2024-09-10', 41, 20),
(471, 4, 'Good quality item. Satisfied with the purchase.', '2024-09-10', 7, 13),
(472, 5, 'Excellent product! I’m very pleased.', '2024-09-10', 24, 17),
(473, 3, 'Average. Does the job but nothing special.', '2024-09-10', 31, 15),
(474, 2, 'Quality is lacking. Very disappointing.', '2024-09-10', 37, 11),
(475, 1, 'Terrible. Will not buy again.', '2024-09-10', 43, 18),
(476, 4, 'Good for the price. Satisfied.', '2024-09-10', 16, 19),
(477, 5, 'Fantastic quality. Exceeds expectations.', '2024-09-10', 22, 12),
(478, 3, 'It’s fine but not exceptional.', '2024-09-10', 29, 13),
(479, 2, 'Disappointing. Quality could be much better.', '2024-09-10', 36, 20),
(480, 1, 'Very poor quality. Do not recommend.', '2024-09-10', 45, 14),
(481, 4, 'Good product with decent features.', '2024-09-10', 19, 11),
(482, 5, 'Wonderful product. Very happy with it.', '2024-09-10', 26, 16),
(483, 3, 'It’s okay. Meets basic needs.', '2024-09-10', 32, 13),
(484, 2, 'Quality is subpar. Not worth it.', '2024-09-10', 39, 18),
(485, 1, 'Very poor quality. Will not buy again.', '2024-09-10', 46, 12),
(486, 4, 'Good quality for the price.', '2024-09-10', 14, 17),
(487, 5, 'Fantastic! I’m very pleased with it.', '2024-09-10', 23, 19),
(488, 3, 'Average. It does the job but could be better.', '2024-09-10', 30, 15),
(489, 2, 'Disappointing. Quality could be much better.', '2024-09-10', 38, 16),
(490, 1, 'Very poor quality. Will not buy again.', '2024-09-10', 46, 18),
(491, 4, 'Good value. Satisfied with the purchase.', '2024-09-10', 14, 19),
(492, 5, 'Wonderful! Exceeded my expectations.', '2024-09-10', 21, 11),
(493, 3, 'Average product. It meets basic needs.', '2024-09-10', 28, 20),
(494, 2, 'Quality is lacking. Not worth it.', '2024-09-10', 34, 13),
(495, 1, 'Very disappointed. The product is defective.', '2024-09-10', 41, 12),
(496, 4, 'Good product. Worth the money.', '2024-09-10', 18, 17),
(497, 5, 'Fantastic quality! I’m very happy.', '2024-09-10', 26, 19),
(498, 3, 'It’s fine but could be better.', '2024-09-10', 33, 20),
(499, 2, 'Disappointing. Quality could be much better.', '2024-09-10', 39, 14),
(500, 1, 'Very poor quality. Will not buy again.', '2024-09-10', 46, 18);
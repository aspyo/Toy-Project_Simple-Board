-- 카테고리 데이터
insert into category(category_id, created_at, updated_at, category_name, description) values (1,  NOW(), NOW(), '프론트엔드 갤러리', '테스트 카테고리 - 프론트엔드');
insert into category(category_id, created_at, updated_at, category_name, description) values (2,  NOW(), NOW(), '백엔드 갤러리', '테스트 카테고리 - 백엔드');

-- 유저 데이터
insert into users(created_at, updated_at,user_id, login_id, password, username) values (NOW(), NOW(), 1, 'testA', 'passwordA', 'userA');
insert into users(created_at, updated_at,user_id, login_id, password, username) values (NOW(), NOW(), 2, 'testB', 'passwordB', 'userB');

-- 게시글 데이터
insert into post(dislikes, likes, category_id, created_at, post_id, updated_at, user_id, content, title) values (0, 0, 1, NOW(), 1, NOW(), 1, null, 'postA');
insert into post(dislikes, likes, category_id, created_at, post_id, updated_at, user_id, content, title) values (0, 0, 1, NOW(), 2, NOW(), 2, null, 'postB');
insert into post(dislikes, likes, category_id, created_at, post_id, updated_at, user_id, content, title) values (0, 0, 1, NOW(), 3, NOW(), 1, null, 'postC');
insert into post(dislikes, likes, category_id, created_at, post_id, updated_at, user_id, content, title) values (0, 0, 1, NOW(), 4, NOW(), 2, null, 'postD');
insert into post(dislikes, likes, category_id, created_at, post_id, updated_at, user_id, content, title) values (0, 0, 1, NOW(), 5, NOW(), 1, null, 'postE');
insert into post(dislikes, likes, category_id, created_at, post_id, updated_at, user_id, content, title) values (0, 0, 1, NOW(), 6, NOW(), 2, null, 'postF');
insert into post(dislikes, likes, category_id, created_at, post_id, updated_at, user_id, content, title) values (0, 0, 1, NOW(), 7, NOW(), 1, null, 'postG');
insert into post(dislikes, likes, category_id, created_at, post_id, updated_at, user_id, content, title) values (0, 0, 1, NOW(), 8, NOW(), 2, null, 'postH');
insert into post(dislikes, likes, category_id, created_at, post_id, updated_at, user_id, content, title) values (0, 0, 1, NOW(), 9, NOW(), 1, null, 'postI');
insert into post(dislikes, likes, category_id, created_at, post_id, updated_at, user_id, content, title) values (0, 0, 1, NOW(), 10, NOW(), 2, null, 'postJ');
insert into post(dislikes, likes, category_id, created_at, post_id, updated_at, user_id, content, title) values (0, 0, 1, NOW(), 11, NOW(), 2, null, 'postK');
insert into post(dislikes, likes, category_id, created_at, post_id, updated_at, user_id, content, title) values (0, 0, 1, NOW(), 12, NOW(), 1, null, 'postL');

-- 카테고리 데이터
insert into category(created_at, updated_at, category_name, description) values (NOW(), NOW(), '프론트엔드 갤러리', '테스트 카테고리 - 프론트엔드');
insert into category(created_at, updated_at, category_name, description) values (NOW(), NOW(), '백엔드 갤러리', '테스트 카테고리 - 백엔드');

-- 유저 데이터
insert into users(created_at, updated_at, login_id, password, username) values (NOW(), NOW(), 'testA', 'passwordA', 'userA');
insert into users(created_at, updated_at, login_id, password, username) values (NOW(), NOW(), 'testB', 'passwordB', 'userB');
insert into users(created_at, updated_at, login_id, password, username) values (NOW(), NOW(), 'testC', 'passwordC', 'userC');

-- 게시글 데이터
insert into post(dislikes, likes, category_id, created_at, updated_at, user_id, content, title) values (0, 0, 1, NOW(), NOW(), 1, '게시글 A의 내용입니다.', 'postA');
insert into post(dislikes, likes, category_id, created_at, updated_at, user_id, content, title) values (0, 0, 1, NOW(), NOW(), 2, '게시글 B의 내용입니다.', 'postB');
insert into post(dislikes, likes, category_id, created_at, updated_at, user_id, content, title) values (0, 0, 1, NOW(), NOW(), 1, '게시글 C의 내용입니다.', 'postC');
insert into post(dislikes, likes, category_id, created_at, updated_at, user_id, content, title) values (0, 0, 1, NOW(), NOW(), 2, '게시글 D의 내용입니다.', 'postD');
insert into post(dislikes, likes, category_id, created_at, updated_at, user_id, content, title) values (0, 0, 1, NOW(), NOW(), 1, '게시글 E의 내용입니다.', 'postE');
insert into post(dislikes, likes, category_id, created_at, updated_at, user_id, content, title) values (0, 0, 1, NOW(), NOW(), 2, '게시글 F의 내용입니다.', 'postF');
insert into post(dislikes, likes, category_id, created_at, updated_at, user_id, content, title) values (0, 0, 1, NOW(), NOW(), 1, '게시글 G의 내용입니다.', 'postG');
insert into post(dislikes, likes, category_id, created_at, updated_at, user_id, content, title) values (0, 0, 1, NOW(), NOW(), 2, '게시글 H의 내용입니다.', 'postH');
insert into post(dislikes, likes, category_id, created_at, updated_at, user_id, content, title) values (0, 0, 1, NOW(), NOW(), 1, '게시글 I의 내용입니다.', 'postI');
insert into post(dislikes, likes, category_id, created_at, updated_at, user_id, content, title) values (0, 0, 1, NOW(), NOW(), 2, '게시글 J의 내용입니다.', 'postJ');
insert into post(dislikes, likes, category_id, created_at, updated_at, user_id, content, title) values (0, 0, 1, NOW(), NOW(), 2, '게시글 K의 내용입니다.', 'postK');
insert into post(dislikes, likes, category_id, created_at, updated_at, user_id, content, title) values (0, 0, 1, NOW(), NOW(), 1, '게시글 L의 내용입니다.', 'postL');

-- 댓글 데이터
insert into comment(created_at, post_id, updated_at, user_id, content) values (NOW(), 12, NOW(), 2, '유저B가 게시글L에 작성한 첫번째 테스트 댓글입니다.');
insert into comment(created_at, post_id, updated_at, user_id, content) values (NOW(), 12, NOW(), 3, '유저C가 게시글L에 작성한 두번째 테스트 댓글입니다.');
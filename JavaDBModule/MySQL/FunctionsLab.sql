-- 1.	Find Book Titles
select title from book_library.books where substring(title,1,3) = 'The' order by id;

-- 2.	Replace Titles
select replace(title,'The','***') as title from book_library.books 
where substring(title,1,3) = 'The' order by id;

-- 3.	Sum Cost of All Books
select FORMAT(sum(cost),2) as total_price from book_library.books;

-- 4.	Days Lived
select concat_ws(' ',first_name, last_name) as 'Full Name',
timestampdiff(day,born, died) as 'Days Lived' from book_library.authors;

-- 5.	Harry Potter Books
select title from book_library.books where title like 'Harry Potter%' order by id;
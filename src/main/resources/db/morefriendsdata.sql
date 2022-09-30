TRUNCATE TABLE "friends" RESTART IDENTITY CASCADE;

INSERT INTO "public"."friends" ("requester_id", "requestee_id", "request_status", "low_id", "high_id") VALUES
(1, 4, 'accepted', 1, 4),
(1, 3, 'accepted', 1, 3),
(5, 6, 'blocked', 5, 6),
(4, 12, 'accepted', 4, 12),
(5, 4, 'accepted', 4, 5),
(8, 10, 'accepted', 8, 10),
(7, 12, 'accepted', 7, 12),
(11, 10, 'accepted', 10, 11),
(1, 12, 'accepted', 1, 12),
(5, 10, 'accepted', 5, 10),
(10, 6, 'blocked', 6, 10),
(8, 3, 'accepted', 3, 8),
(1, 6, 'accepted', 1, 6),
(1, 5, 'blocked', 1, 5),
(1, 8, 'accepted', 1, 8),
(10, 3, 'accepted', 3, 10),
(3, 11, 'accepted', 3, 11),
(11, 2, 'accepted', 2, 11),
(11, 9, 'accepted', 9, 11),
(11, 7, 'accepted', 7, 11),
(2, 3, 'pending', 2, 3),
(2, 10, 'accepted', 2, 10),
(2, 4, 'pending', 2, 4),
(2, 12, 'accepted', 2, 12),
(2, 9, 'accepted', 2, 9),
(9, 1, 'accepted', 1, 9),
(9, 10, 'accepted', 9, 10),
(9, 7, 'accepted', 7, 9),
(12, 6, 'pending', 6, 12),
(12, 3, 'pending', 3, 12),
(8, 2, 'blocked', 2, 8),
(8, 12, 'blocked', 8, 12),
(8, 7, 'blocked', 7, 8),
(8, 4, 'pending', 4, 8),
(5, 7, 'blocked', 5, 7),
(5, 2, 'blocked', 2, 5),
(5, 9, 'blocked', 5, 9),
(2, 1, 'blocked', 1, 2),
(2, 6, 'blocked', 2, 6),
(2, 7, 'blocked', 2, 7);
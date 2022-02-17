ALTER TABLE users ADD avatarpath VARCHAR(120);
ALTER TABLE users ALTER avatarpath SET DEFAULT '/uploads-dir/avatar/default.png';
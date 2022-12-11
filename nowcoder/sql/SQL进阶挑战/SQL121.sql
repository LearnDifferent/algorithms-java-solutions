alter table
  examination_info
add
  index idx_duration(duration);

alter table
  examination_info
add
  unique index uniq_idx_exam_id(exam_id);

alter table
  examination_info
add
  fulltext index full_idx_tag(tag);

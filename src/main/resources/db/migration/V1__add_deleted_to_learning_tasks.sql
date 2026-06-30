-- Add soft-delete column to learning_tasks for audit/soft-delete
ALTER TABLE learning_tasks
ADD COLUMN IF NOT EXISTS deleted boolean NOT NULL DEFAULT false;

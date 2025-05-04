--- Пользователь ---
CREATE TABLE IF NOT EXISTS public.user (
    id UUID PRIMARY KEY,
    login TEXT NOT NULL,
    password TEXT NOT NULL,
    email TEXT NOT NULL,
    "role" TEXT NOT NULL,
    deleted BOOLEAN NOT NULL DEFAULT false,
    create_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    update_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now()
);

CREATE UNIQUE INDEX IF NOT EXISTS idx_user_login ON public.user USING btree (login);
CREATE INDEX IF NOT EXISTS idx_user_password ON public.user USING btree (password);

COMMENT ON TABLE public.user IS 'Информация о пользователе';
COMMENT ON COLUMN public.user.id IS 'Уникальный идентификатор пользователя';
COMMENT ON COLUMN public.user.login IS 'Логин пользователя';
COMMENT ON COLUMN public.user.password IS 'Пароль пользователя';
COMMENT ON COLUMN public.user.email IS 'Email пользователя';
COMMENT ON COLUMN public.user."role" IS 'Роль пользователя';
COMMENT ON COLUMN public.user.deleted IS 'Признак, удалена ли запись';
COMMENT ON COLUMN public.user.create_at IS 'Временная метка создания записи в БД';
COMMENT ON COLUMN public.user.update_at IS 'Временная метка изменения записи в БД';

--- Задача ---
CREATE TABLE IF NOT EXISTS public.task (
    id UUID PRIMARY KEY,
    "number" BIGSERIAL NOT NULL,
    title TEXT NOT NULL,
    description TEXT,
    status TEXT NOT NULL DEFAULT 'NEW',
    priority TEXT NOT NULL DEFAULT 'LOW',
    date_due TIMESTAMP WITH TIME ZONE,
    date_fact TIMESTAMP WITH TIME ZONE,
    project_id UUID,
    assigned_user_id UUID,
    create_user_id UUID NOT NULL,
    deleted BOOLEAN NOT NULL DEFAULT false,
    create_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    update_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now()
);

COMMENT ON TABLE public.task IS 'Информация о задаче';
COMMENT ON COLUMN public.task.id IS 'Уникальный идентификатор задачи';
COMMENT ON COLUMN public.task.number IS 'Номер задачи';
COMMENT ON COLUMN public.task.title IS 'Название задачи';
COMMENT ON COLUMN public.task.description IS 'Описание задачи';
COMMENT ON COLUMN public.task.status IS 'Статус задачи';
COMMENT ON COLUMN public.task.priority IS 'Приоритет';
COMMENT ON COLUMN public.task.date_due IS 'Дата выполнения задачи предварительная/оценочная';
COMMENT ON COLUMN public.task.date_fact IS 'Дата выполнения задачи фактическая, когда закрыли задачу';
COMMENT ON COLUMN public.task.project_id IS 'Идентификатор проекта к которому относиться задача';
COMMENT ON COLUMN public.task.assigned_user_id IS 'Идентификатор пользователя на которого назначена задача';
COMMENT ON COLUMN public.task.create_user_id IS 'Идентификатор пользователя который создал задачу';
COMMENT ON COLUMN public.task.deleted IS 'Признак, удалена ли запись';
COMMENT ON COLUMN public.task.create_at IS 'Временная метка создания записи в БД';
COMMENT ON COLUMN public.task.update_at IS 'Временная метка изменения записи в БД';

--- Проект ---
CREATE TABLE IF NOT EXISTS public.project (
    id UUID PRIMARY KEY,
    "name" TEXT NOT NULL,
    description TEXT,
    create_user_id UUID NOT NULL,
    update_user_id UUID,
    deleted BOOLEAN NOT NULL DEFAULT false,
    create_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    update_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now()
);

COMMENT ON TABLE public.project IS 'Информация о проекте';
COMMENT ON COLUMN public.project.id IS 'Уникальный идентификатор проекта';
COMMENT ON COLUMN public.project.name IS 'Название проекта';
COMMENT ON COLUMN public.project.description IS 'Описание проекта';
COMMENT ON COLUMN public.project.create_user_id IS 'Идентификатор пользователя создавшего проект';
COMMENT ON COLUMN public.project.update_user_id IS 'Идентификатор пользователя изменившего проект';
COMMENT ON COLUMN public.project.deleted IS 'Признак, удалена ли запись';
COMMENT ON COLUMN public.project.create_at IS 'Временная метка создания записи в БД';
COMMENT ON COLUMN public.project.update_at IS 'Временная метка изменения записи в БД';

--- Комментарии ---
CREATE TABLE IF NOT EXISTS public.comment_task (
    id UUID PRIMARY KEY,
    create_user_id UUID NOT NULL,
    update_user_id UUID,
    task_id UUID NOT NULL,
    content TEXT NOT NULL,
    deleted BOOLEAN NOT NULL DEFAULT false,
    create_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    update_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now()
);

COMMENT ON TABLE public.comment_task IS 'Информация о комментариях задачи';
COMMENT ON COLUMN public.comment_task.id IS 'Уникальный идентификатор комментария';
COMMENT ON COLUMN public.comment_task.create_user_id IS 'Пользователь добавивший комментарий';
COMMENT ON COLUMN public.comment_task.update_user_id IS 'Пользователь изменивший комментарий';
COMMENT ON COLUMN public.comment_task.task_id IS 'Идентификатор задачи';
COMMENT ON COLUMN public.comment_task.content IS 'Комментарий';
COMMENT ON COLUMN public.comment_task.deleted IS 'Признак, удалена ли запись';
COMMENT ON COLUMN public.comment_task.create_at IS 'Временная метка создания записи в БД';
COMMENT ON COLUMN public.comment_task.update_at IS 'Временная метка изменения записи в БД';
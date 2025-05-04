ALTER TABLE public.task
    ADD CONSTRAINT task_assigned_user_id_fkey FOREIGN KEY (assigned_user_id) REFERENCES public.user(id),
    ADD CONSTRAINT task_create_user_id_fkey FOREIGN KEY (create_user_id) REFERENCES public.user(id);

ALTER TABLE public.project
    ADD CONSTRAINT project_create_user_id_fkey FOREIGN KEY (create_user_id) REFERENCES public.user(id),
    ADD CONSTRAINT project_update_user_id_fkey FOREIGN KEY (update_user_id) REFERENCES public.user(id);

ALTER TABLE public.comment_task
    ADD CONSTRAINT comment_task_create_user_id_fkey FOREIGN KEY (create_user_id) REFERENCES public.user(id),
    ADD CONSTRAINT comment_task_update_user_id_fkey FOREIGN KEY (update_user_id) REFERENCES public.user(id),
    ADD CONSTRAINT comment_task_task_id_fkey FOREIGN KEY (task_id) REFERENCES public.task(id);
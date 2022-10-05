import { pureget, post } from "@/common/ajax";

export const get_basic_information = () => pureget("/profile/BI/get");

export const update_basic_information = form_data =>
  post("/profile/BI/update", form_data);

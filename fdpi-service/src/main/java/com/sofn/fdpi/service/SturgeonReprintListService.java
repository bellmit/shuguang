package com.sofn.fdpi.service;

import java.util.List;

public interface SturgeonReprintListService   {

    void saveOrUpdate(String reprintId, List<String> signboardIds);

    List<String> listByReprintId(String reprintId);
}

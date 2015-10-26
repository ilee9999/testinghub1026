package com.hkesports.matchticker.repository;

import com.hkesports.matchticker.model.Data;
import com.hkesports.matchticker.repository.custom.DataDaoCustom;
import com.hkesports.matchticker.repository.factory.GenericRepository;

public interface DataDao extends DataDaoCustom, GenericRepository<Data, Long> {
}

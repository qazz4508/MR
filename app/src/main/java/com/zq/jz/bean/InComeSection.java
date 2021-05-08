package com.zq.jz.bean;

import com.chad.library.adapter.base.entity.SectionEntity;
import com.zq.jz.db.table.InCome;

public class InComeSection extends SectionEntity<InCome> {

    public InComeSection(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public InComeSection(InCome inCome) {
        super(inCome);
    }
}

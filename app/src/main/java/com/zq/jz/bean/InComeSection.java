package com.zq.jz.bean;

import com.chad.library.adapter.base.entity.SectionEntity;
import com.zq.jz.db.table.InComePay;

public class InComeSection extends SectionEntity<InComePay> {

    public InComeSection(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public InComeSection(InComePay inComePay) {
        super(inComePay);
    }
}

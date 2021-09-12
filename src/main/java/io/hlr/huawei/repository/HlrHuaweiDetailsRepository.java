package io.hlr.huawei.repository;

import io.hlr.huawei.domain.HlrHuaweiDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HlrHuaweiDetailsRepository extends JpaRepository<HlrHuaweiDetails, String> {

}

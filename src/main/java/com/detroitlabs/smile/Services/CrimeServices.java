package com.detroitlabs.smile.Services;

import com.detroitlabs.smile.Model.CrimeDataModel.TopCrimeData;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CrimeServices {
    public TopCrimeData getHighCrimedata(String blockID){
        RestTemplate rt = new RestTemplate();
        return rt.getForObject("https://data.detroitmi.gov/resource/6gdg-y3kf.json?$where=arrest_charge in('09001'," +
                        "'09002','09003',' 09005','12000','13002','36004','49000','52002','52003','54001')AND year in" +
                        "('2018','2019')&block_id=" + blockID
                ,TopCrimeData.class);

    }

    public TopCrimeData getLowCrimedata(String blockID){
        RestTemplate rt = new RestTemplate();
        return rt.getForObject("https://data.detroitmi.gov/resource/6gdg-y3kf.json?$where=arrest_charge in('10001'," +
                        " '13001','13003','23001','23002','23005','23006','24001','24002','35002','36003','37000','40002'," +
                        "'40003','52001','53001','53002','63000','73000')AND year in('2018','2019')&block_id=" + blockID
                ,TopCrimeData.class);

    }
}

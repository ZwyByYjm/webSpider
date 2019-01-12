package com.webSpider.spider.process;


import com.webSpider.pojo.CleMusicInfoTemp1;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import utils.FileUtils;

import java.util.Random;

/**
 * @author 印佳明
 * @create 2017-10-26 19:59
 */
public class CleMusicInfoProcess implements PageProcessor {

    public static final String[] COOKIES = new String[]{
//            "_ntes_nuid=3f699717063528cda9b23f6ac8a8d305; _ga=GA1.2.1350381713.1513686416; __gads=ID=c8221b335307e0de:T=1514885571:S=ALNI_MatArhY-Cc9E8Yy-yqcaYCj9xNCdw; vjuids=6e1acf2df.160b6376d52.0.9f88a3a88c3ff; mail_psc_fingerprint=d6e1c49113b22130cd01255775fa3dea; NTES_CMT_USER_INFO=118404385%7C%E6%9C%89%E6%80%81%E5%BA%A6%E7%BD%91%E5%8F%8B073Hkx%7C%7Cfalse%7Cam1uY3V0QDE2My5jb20%3D; nts_mail_user=jmncut@163.com:-1:1; _iuqxldmzr_=32; __utmz=94650624.1530666604.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); P_INFO=jmncut@163.com|1533461848|0|hzhr|00&99|bej&1533461822&hzhr#bej&null#10#0#0|156778&1|hzhr|jmncut@163.com; mp_MA-9ADA-91BF1A6C9E06_hubble=%7B%22sessionReferrer%22%3A%20%22https%3A%2F%2Fcampus.163.com%2Fapp%2Fpersonal%2Finterview%22%2C%22updatedTime%22%3A%201533469827560%2C%22sessionStartTime%22%3A%201533469827555%2C%22sendNumClass%22%3A%20%7B%22allNum%22%3A%202%2C%22errSendNum%22%3A%200%7D%2C%22deviceUdid%22%3A%20%22fb7144bc-c66a-48a1-9448-4ccb69ba9b1e%22%2C%22persistedTime%22%3A%201533376685421%2C%22LASTEVENT%22%3A%20%7B%22eventId%22%3A%20%22da_screen%22%2C%22time%22%3A%201533469827560%7D%2C%22sessionUuid%22%3A%20%2205981d6a-b73b-498f-b56c-1f76d96c4354%22%7D; __f_=1542348544305; UM_distinctid=167ab0c683b305-0f42cf6457120d-b79193d-1fa400-167ab0c683c9bf; vjlast=1514885574.1544967136.12; vinfo_n_f_l_n3=b556854f4af88be7.1.13.1514885573981.1544787052701.1544967174133; _ntes_nnid=3f699717063528cda9b23f6ac8a8d305,1545722470603; WM_TID=hLxcyJ8Y4vJFAVQEVRZpL%2Fl1XvMMYgig; __remember_me=true; WM_NI=aI%2Bs8cQGsK3TGn9BxtlRp51fD8X64B3lR4Dyhz3Lf5OFocmqkGtk5rhxFPXicQ1W1kFtLG4HNvNx9fraO9nSYzObQ5ktKTZUdHWHbx%2FgsZVLDRf5Q1XsoC%2BhMGAMFQqFR2s%3D; WM_NIKE=9ca17ae2e6ffcda170e2e6ee93b56ab8aea3bae648af968ea2d84e829a9faab73b83acfaccd148b19b999ab32af0fea7c3b92aa397e594bb699399bdacb26085b2f7a3fb80b08ec0d6b46e88e898d7b74af1b49ed5c773aca6fdade53f8d9aa184f07ba999bc8cb87aaca88ab1db63a18fffb8b46186ac84a8b7448eaa8299bc6eb2bf81daed599ba68183d43fa9ac85b5d95fa89899a9e174edb9bdbbd548888eaad6d43b9cada1a4aa648f998faec55bb39a9ed3f237e2a3; __utmc=94650624; __utma=94650624.1350381713.1513686416.1547177952.1547185095.15; MUSIC_U=9c24379b9ac2d7c2efca1ea90a87f2b3b043d173204a83e2b90e1b53951b133732fb6615e0249f62a8c1d037e4e928df31b299d667364ed3; __csrf=5958547ce8edcb6beab321838509677f; __utmb=94650624.8.10.1547185095; JSESSIONID-WYYY=uhJmAgvCizZ%2B2d06I4CknVeUeOhoDwNd7a%2Bn9M6wMSG8rITply1I61ECaXurnvqslG8%2FbsGCHDcdlhKbopF%2FBnZ3pAQ9Qh7DvkPTqxqscnyxyx7Cl%5CEFiIW3umJV0gO2NQPdJUd%5CGAR%2BeZ9k%2F3598Q1%5Cni8s7QNUToij5G10AE%5C8mnbz%3A1547188635028",
//            "_ntes_nuid=3f699717063528cda9b23f6ac8a8d305; _ga=GA1.2.1350381713.1513686416; __gads=ID=c8221b335307e0de:T=1514885571:S=ALNI_MatArhY-Cc9E8Yy-yqcaYCj9xNCdw; vjuids=6e1acf2df.160b6376d52.0.9f88a3a88c3ff; mail_psc_fingerprint=d6e1c49113b22130cd01255775fa3dea; NTES_CMT_USER_INFO=118404385%7C%E6%9C%89%E6%80%81%E5%BA%A6%E7%BD%91%E5%8F%8B073Hkx%7C%7Cfalse%7Cam1uY3V0QDE2My5jb20%3D; nts_mail_user=jmncut@163.com:-1:1; _iuqxldmzr_=32; __utmz=94650624.1530666604.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); P_INFO=jmncut@163.com|1533461848|0|hzhr|00&99|bej&1533461822&hzhr#bej&null#10#0#0|156778&1|hzhr|jmncut@163.com; mp_MA-9ADA-91BF1A6C9E06_hubble=%7B%22sessionReferrer%22%3A%20%22https%3A%2F%2Fcampus.163.com%2Fapp%2Fpersonal%2Finterview%22%2C%22updatedTime%22%3A%201533469827560%2C%22sessionStartTime%22%3A%201533469827555%2C%22sendNumClass%22%3A%20%7B%22allNum%22%3A%202%2C%22errSendNum%22%3A%200%7D%2C%22deviceUdid%22%3A%20%22fb7144bc-c66a-48a1-9448-4ccb69ba9b1e%22%2C%22persistedTime%22%3A%201533376685421%2C%22LASTEVENT%22%3A%20%7B%22eventId%22%3A%20%22da_screen%22%2C%22time%22%3A%201533469827560%7D%2C%22sessionUuid%22%3A%20%2205981d6a-b73b-498f-b56c-1f76d96c4354%22%7D; __f_=1542348544305; Province=010; City=010; UM_distinctid=167ab0c683b305-0f42cf6457120d-b79193d-1fa400-167ab0c683c9bf; vjlast=1514885574.1544967136.12; vinfo_n_f_l_n3=b556854f4af88be7.1.13.1514885573981.1544787052701.1544967174133; _ntes_nnid=3f699717063528cda9b23f6ac8a8d305,1545722470603; WM_TID=hLxcyJ8Y4vJFAVQEVRZpL%2Fl1XvMMYgig; __utma=94650624.1350381713.1513686416.1545831224.1545961870.6; __utmc=94650624; WM_NI=%2B%2BeoydvT3ItGKWCg9ZFZqeEIVkXUew%2BxcIl5T%2BBky4VHQiWljrPfZ4TiB8sezS5CCT8TUU5O%2BFX%2FmKNF0JCXz9smJlSnfPHuQncg0s80XDeTlpH3XaM%2F%2F6jq41jZUEKwb3o%3D; WM_NIKE=9ca17ae2e6ffcda170e2e6ee8de845aca6bcaecc3d83ac8ab7c45b928f8f84bc45f5b08788d1688cb599dacc2af0fea7c3b92ae9f5ab90fc70b68cfdaaf74d89898d88bc528686bf86c84396b598b6e87de9a8adaaef3f98f58e8cfc4096989f8ad668aaaea087d94098b78ed9f26fba8dfba4c260fc8c9bb6b845e99a98d0f0418e9a00abc254bae7bd8ec96891b2aab1cb7f93b2ad88b379fcf098a5e860b0b088d4db6b8ff1e1d7e97bf8efa6bbd84f89a9aed4ee37e2a3; JSESSIONID-WYYY=29Vg8X2c7f%2FYbk58gq%2F7vVP1qBHNsZaP%2BoPT%2FlijA%5CgG3IijCoGZYZYS9eMgHB1Zo1p34%2BjcMruT%2FQgIr3VWm16iNI5yGlsEHMeG6WJ6xd5%2FiPrEQ%2Bz5rqgoGC6yijlf5zA5F6uyQZccnsMmY5qbOlT99VCy66NtkJwD%2BhI1YWCdH%2Fui%3A1545965410077; __utmb=94650624.6.10.1545961870 ",
            "__s_=1; _ntes_nnid=eae5803bb40e2409e51e275e6edc32ab,1505205076692; _ntes_nuid=eae5803bb40e2409e51e275e6edc32ab; usertrack=c+xxC1nJqmQrEPdeDD4OAg==; _ga=GA1.2.577059788.1506388583; JSESSIONID-WYYY=0wK8%5C6FjZBp2BFAf%2BejvH8ok8Cv7ICwHlioNZ%2BCVTxeVemV6QOTRKsNa7i%5CC04I%2FJAj7S%5Cdjllur0uTo%2Bj87GdfYPaKqyl2ygbOYTOs57JaAWFR9DeE7A2hGVV4KDiNY8HAFtpE%2Bh8lUjwKcfxyIny%2FJKw759t7UqAUN36Xe20l1gy8d%3A1509005917212; _iuqxldmzr_=32; __utma=94650624.577059788.1506388583.1509004120.1509004120.1; __utmb=94650624.2.10.1509004120; __utmc=94650624; __utmz=94650624.1509004120.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none)",
            "usertrack=ezq0plqg5ll9iN+iBX0lAg==; _ntes_nnid=dee53655f8948114a13189e760977483,1520494170027; _ntes_nuid=dee53655f8948114a13189e760977483; _ga=GA1.2.1113881333.1520494171; _iuqxldmzr_=32; P_INFO=470181125@qq.com|1521445155|0|hzhr|00&99|null&null&null#not_found&null#10#0|&0||470181125@qq.com; __gads=ID=b49498acd6cd1dea:T=1530080684:S=ALNI_MaJritGzdCfnxFK3QRXKK6X4DTr0w; vjuids=46d498748.1643fea6bbf.0.09f91908aeb47; vinfo_n_f_l_n3=4f014492afe600ee.1.0.1530080685025.0.1530081415333; WM_TID=wb6y09ZBWktFFRRVVQcseUJ0jh5dNuXx; mp_MA-9ADA-91BF1A6C9E06_hubble=%7B%22sessionReferrer%22%3A%20%22https%3A%2F%2Fcampus.163.com%2F%22%2C%22updatedTime%22%3A%201539593927211%2C%22sessionStartTime%22%3A%201539593927208%2C%22sendNumClass%22%3A%20%7B%22allNum%22%3A%201%2C%22errSendNum%22%3A%200%7D%2C%22deviceUdid%22%3A%20%223047c832-c4d7-4458-a5d6-fff4a52f8690%22%2C%22persistedTime%22%3A%201539593927204%2C%22LASTEVENT%22%3A%20%7B%22eventId%22%3A%20%22da_screen%22%2C%22time%22%3A%201539593927212%7D%2C%22sessionUuid%22%3A%20%22ccab02c3-e848-464c-b6e6-00b678db79e2%22%7D; vjlast=1530080685.1539601693.12; __utmz=94650624.1545910930.12.5.utmcsr=baidu|utmccn=(organic)|utmcmd=organic; JSESSIONID-WYYY=v%5CWecCNfrPD3%2BWYqAyHINVlIdu2TdG%2FXa4eWTw9Gx5RtE5XBaPwBy2iUi4AScqnUMz5x4TbfQckMH2tprdRJgWV4eqeKe7jS7WH5bFGg%5Cp8BEE%5C%2BRgolBwjTprRnnNPs3X%5C1QrF07ar4DGen5cdDx8pdPhcho9Xcq3YcXRIJr6HIOp44%3A1547190414172; __utma=94650624.1113881333.1520494171.1546481959.1547188615.14; __utmc=94650624; WM_NI=iSZjLOeDZ%2BPiEn7pQnBsyc71Bw3FeP4w7HE2CFi2i9qQG4oBzu5qDti5HEPPa2v1yR9HPYUkq7qzoMSWcJNxvmp3w3FKoMSSRqKJhvvyeVjFaWqpN4DLLIeEh07gDn0OV1Q%3D; WM_NIKE=9ca17ae2e6ffcda170e2e6ee86fb42988f82b1f14296b08fb7c84e938b8f84f76d86f1ae8ec849a6be8aa3e52af0fea7c3b92aab8d968bcf45a79c96ccd36387b2f991d46682adb784b63b82a9fda7e85cf8a9f9a9c83a819aa7d3ec33b38f89d5ee4abaabfb92c780abeb88a6d35ca3eebad2fc5afb98a0bad861b3e9bf9bee479189adabcd74b6b3c0d4f6398695a1aed25992af85abeb7eb6a7abb7e94ea389bf91cd40889484aae84facf0f988c766f4919ad2d437e2a3; __utmb=94650624.3.10.1547188615",
            "_ntes_nuid=53f8777a908adc1fade5f4d408b71734; vjuids=-cc3f4d4f4.15755ef852d.0.a64594b096de3; __gads=ID=96e8a37b864614e1:T=1477567255:S=ALNI_MZDp-klfpudEJCh0C3_ErZXMm4Brw; __s_=1; NTES_CMT_USER_INFO=118404385%7C%E6%9C%89%E6%80%81%E5%BA%A6%E7%BD%91%E5%8F%8B073Hkx%7C%7Cfalse%7Cam1uY3V0QDE2My5jb20%3D; __utma=187553192.135554208.1475672367.1504746836.1505093225.13; __utmz=187553192.1505093225.13.10.utmcsr=open.163.com|utmccn=(referral)|utmcmd=referral|utmcct=/ted/; __oc_uuid=13902340-f9bc-11e6-a69c-b7ce7eb90a7c; _ntes_nnid=53f8777a908adc1fade5f4d408b71734,1506485249602; vjlast=1474615543.1508207044.11; vinfo_n_f_l_n3=dd7c1e1c7c903f37.1.20.1474615543099.1506490530904.1508207185197; usertrack=c+xxC1n22F8nk0YmAxDjAg==; _ga=GA1.2.135554208.1475672367; P_INFO=jmncut@163.com|1505093238|2|open|00&99|bej&1504142674&open#bej&null#10#0#0|156778&1|open&study|jmncut@163.com; JSESSIONID-WYYY=dIkS2jVEkXhSWroYjpJEO4Klbf6dIsex0w1jCmWX6fJZvyySw7MOp%2FhIQksmVm%2Bc2j%2BDCfobEyFnZBYrROciT4mDQUagIb4wWTxxxPxP855YBFRjmD6NSceJ9MIazQ4vZ7kvzVV9z5TwuCMlNQ5IyHNAxbhqEm1nJjEkGu8tC%5CYDnJwH%3A1511919295977; _iuqxldmzr_=32; __utma=94650624.135554208.1475672367.1511872233.1511918327.13; __utmb=94650624.2.10.1511918327; __utmc=94650624; __utmz=94650624.1493814912.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); __remember_me=true; MUSIC_U=e253743d84ed11a4aeeade41d1a92277dbda2dccfc340ef8afc61c610e10e57a657b34efc0a47cb8f6f5b8899e2fc54b8bafcdfe5ad2b092; __csrf=9b528e36a413da944ca00ea70beef1be",
            "_ntes_nuid=3f699717063528cda9b23f6ac8a8d305; _ga=GA1.2.1350381713.1513686416; __gads=ID=c8221b335307e0de:T=1514885571:S=ALNI_MatArhY-Cc9E8Yy-yqcaYCj9xNCdw; vjuids=6e1acf2df.160b6376d52.0.9f88a3a88c3ff; mail_psc_fingerprint=d6e1c49113b22130cd01255775fa3dea; NTES_CMT_USER_INFO=118404385%7C%E6%9C%89%E6%80%81%E5%BA%A6%E7%BD%91%E5%8F%8B073Hkx%7C%7Cfalse%7Cam1uY3V0QDE2My5jb20%3D; nts_mail_user=jmncut@163.com:-1:1; _iuqxldmzr_=32; __utmz=94650624.1530666604.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); P_INFO=jmncut@163.com|1533461848|0|hzhr|00&99|bej&1533461822&hzhr#bej&null#10#0#0|156778&1|hzhr|jmncut@163.com; mp_MA-9ADA-91BF1A6C9E06_hubble=%7B%22sessionReferrer%22%3A%20%22https%3A%2F%2Fcampus.163.com%2Fapp%2Fpersonal%2Finterview%22%2C%22updatedTime%22%3A%201533469827560%2C%22sessionStartTime%22%3A%201533469827555%2C%22sendNumClass%22%3A%20%7B%22allNum%22%3A%202%2C%22errSendNum%22%3A%200%7D%2C%22deviceUdid%22%3A%20%22fb7144bc-c66a-48a1-9448-4ccb69ba9b1e%22%2C%22persistedTime%22%3A%201533376685421%2C%22LASTEVENT%22%3A%20%7B%22eventId%22%3A%20%22da_screen%22%2C%22time%22%3A%201533469827560%7D%2C%22sessionUuid%22%3A%20%2205981d6a-b73b-498f-b56c-1f76d96c4354%22%7D; __f_=1542348544305; UM_distinctid=167ab0c683b305-0f42cf6457120d-b79193d-1fa400-167ab0c683c9bf; vjlast=1514885574.1544967136.12; vinfo_n_f_l_n3=b556854f4af88be7.1.13.1514885573981.1544787052701.1544967174133; _ntes_nnid=3f699717063528cda9b23f6ac8a8d305,1545722470603; WM_TID=hLxcyJ8Y4vJFAVQEVRZpL%2Fl1XvMMYgig; JSESSIONID-WYYY=1rqB5kPtVVKoIa%2FtZF%2BjOjgpIRlp0hgS9pgDlxtle6Yv7MpQt6nWmX54ajZHO%2FQqKlDM%2B1xtNFM0Z2HPVfBMgQJBdMTxQYsFpdNERZUz7SJb2YaxmRFUUFUZ6k%2FXXqib1%2FbNssB6K7SR8Ob7BASuEmMblgV5rCWHACNY%5CEgzzVuvMk2M%3A1546064377171; __utma=94650624.1350381713.1513686416.1545961870.1546062577.7; __utmc=94650624; WM_NI=Vnimc9%2Bpgc5soXJZzeje6LqvEoUOXSaS08i3LjJpfBH%2FseuQmvDJC9pM3LvapdodMq74q%2F0VGWVQxSnhn8v6rj7C%2BpvDHjBvgvK%2FpQx4YbV7sXZnzpeyfjGKAYuGVj7DVDk%3D; WM_NIKE=9ca17ae2e6ffcda170e2e6eed3f87aab89bda8d1548a9a8ba7c45f979b9fafbb5cb6f18b88d463a59eafa2f72af0fea7c3b92a96b2a78ee233e9b0a984b879ad8886bbf96faabbfaadf47e87b19bb0db59a38d00d9ec3990b68691e54a8cf1bd86f85bafaa8cabc233b493fdadf56bb4bca185d07bf5ecbfd0b35e85899ab2fb59aeb8a2d9f53eb2b6f9a4aa7cb69c888bc17ea5aa85d0f35bbcec9783d53db188968ff97ca7a7aa8bc6418387bba8d562f59c9aa5ea37e2a3; __remember_me=true; MUSIC_U=9c24379b9ac2d7c2efca1ea90a87f2b3e80080b3f104a8ab5d1d58ee505d4821b2906683f5d3714bf6adcaa13631bd97a70b41177f9edcea; __csrf=b62af141671842e03e4a18aa776e0779; __utmb=94650624.50.10.1546062577",
            "usertrack=ezq0plqg5ll9iN+iBX0lAg==; _ntes_nnid=dee53655f8948114a13189e760977483,1520494170027; _ntes_nuid=dee53655f8948114a13189e760977483; _ga=GA1.2.1113881333.1520494171; _iuqxldmzr_=32; P_INFO=470181125@qq.com|1521445155|0|hzhr|00&99|null&null&null#not_found&null#10#0|&0||470181125@qq.com; __gads=ID=b49498acd6cd1dea:T=1530080684:S=ALNI_MaJritGzdCfnxFK3QRXKK6X4DTr0w; vjuids=46d498748.1643fea6bbf.0.09f91908aeb47; vinfo_n_f_l_n3=4f014492afe600ee.1.0.1530080685025.0.1530081415333; WM_TID=wb6y09ZBWktFFRRVVQcseUJ0jh5dNuXx; mp_MA-9ADA-91BF1A6C9E06_hubble=%7B%22sessionReferrer%22%3A%20%22https%3A%2F%2Fcampus.163.com%2F%22%2C%22updatedTime%22%3A%201539593927211%2C%22sessionStartTime%22%3A%201539593927208%2C%22sendNumClass%22%3A%20%7B%22allNum%22%3A%201%2C%22errSendNum%22%3A%200%7D%2C%22deviceUdid%22%3A%20%223047c832-c4d7-4458-a5d6-fff4a52f8690%22%2C%22persistedTime%22%3A%201539593927204%2C%22LASTEVENT%22%3A%20%7B%22eventId%22%3A%20%22da_screen%22%2C%22time%22%3A%201539593927212%7D%2C%22sessionUuid%22%3A%20%22ccab02c3-e848-464c-b6e6-00b678db79e2%22%7D; vjlast=1530080685.1539601693.12; __utmz=94650624.1545910930.12.5.utmcsr=baidu|utmccn=(organic)|utmcmd=organic; __utma=94650624.1113881333.1520494171.1546481959.1547188615.14; __utmc=94650624; WM_NI=GAfsmXsxv3Gv4Y1Po06j%2Fre0xiUk5TcShbHPYE7fI41OP6XfgcOitnMW3bv6gUxRv6AxHVnLpA1WCudzfJqpgPCCqhEZ75rpZq75yBkWc57OiJe9UvYCiC2YJN9i4UeLbmw%3D; WM_NIKE=9ca17ae2e6ffcda170e2e6eeaffb258dbd9fa3c521afef8bb6c85f929e9a84bc3c86aaba96d946fbf5afd7b82af0fea7c3b92a859faa94f054b1a8bad4c67ef887ffb1ee3cf6b6e193e83cbcb79b8cb860a18da883cb5c9b9bf987cc40f391a0d7db6ea5bd818af7668f89fd83f53ea58bb8b3f53e83a888b7ed48b49fffb8c27fa8a6aaabf47ca5908da4f4648786f9d6f17c9aaff9d6fc4aa989add6b645ab96abdad87e9b9da7a4b1538a92f78ab75fa794ae8ef637e2a3; JSESSIONID-WYYY=BZxYGB%2B6P1GprPZvErtg8%2F0J4%2BsINqArX0T%2FokxR%2F7TYeX48DZMPQgBzDTauYv8PZYo%2Fo1oMp7Bkyvl1IegsRl7j3ux1TxehW700ys4Ec2muZSGkj%2FfX%2FT5XhT2DzN%5Cgns3BmNSq9RdEhpvTMiJ%2ByXGDXv%2Bk136qrPSac7kj1ht31xAi%3A1547280991513"
    };

    public static final String[] AGENTS = new String[]{
            "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2471.2 Safari/537.36",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.109 Safari/537.36",
            "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US; rv:1.9.1.6) Gecko/20091201 Firefox/3.5.6",
            "Mozilla/5.0 (Windows NT 6.2) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.12 Safari/535.11",
            "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; Trident/6.0)",
            "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36",
            "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.104 Safari/537.36 Core/1.53.4033.400 QQBrowser/9.6.12624.400"};
    private Site site = Site.me().setRetryTimes(3).setCharset("UTF-8")
            .setUserAgent(AGENTS[new Random().nextInt(6)])
            .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
            .addHeader("Accept-Encoding", "gzip, deflate, br")
            .addHeader("Accept-Language", "zh-CN,zh;q=0.8")
            .addHeader("Cache-Control", "max-age=0")
            .addHeader("Connection", "keep-alive")
            .addHeader("Referer", "http://music.163.com/")
            .addHeader("Host", "music.163.com")
            .addHeader("Upgrade-Insecure-Requests", "1")
            .setSleepTime(0)
            .setRetryTimes(3)
            .setCycleRetryTimes(100)
            //.setHttpProxy(IPS[new Random().nextInt(6)])
            //.addCookie("Cookie", COOKIES[new Random().nextInt(3)] + System.currentTimeMillis());
            .addCookie("Cookie", COOKIES[new Random().nextInt(5)]);


    @Override
    public Site getSite() {
        return site;
    }


    @Override
    public void process(Page page) {
        try {
            site.setUserAgent(AGENTS[new Random().nextInt(5)]);
            Thread.sleep(new Random().nextInt(30) * 100);

        } catch (Exception e) {
            e.printStackTrace();
        }
        processForMusicInfo(page);

    }

    /**
     * @Author 印佳明
     * @Date 2017/11/21 14:14
     * 歌曲详细信息
     * id     musicid    name    createdtime    composerid    commentcount
     * musicurl    tag    emotionid    styleid    languageid  albumid
     */
    public void processForMusicInfo(Page page) {
        System.out.println("******开始爬取歌曲详细信息+" + page.getUrl().toString() + "+*******");
        try {
            CleMusicInfoTemp1 cleMusicInfoTemp1 = new CleMusicInfoTemp1();
            cleMusicInfoTemp1.setMusicid(page.getUrl().toString().split("=")[1].trim());
            System.out.println(page.getHtml().xpath("//div[@class='tit']/text()"));
            cleMusicInfoTemp1.setName(page.getHtml().xpath("//div[@class='tit']/em/text()").toString().trim());
            System.out.println(page.getHtml().xpath("//div[@class='cnt']/p/span/a/@href"));
            cleMusicInfoTemp1.setComposerid(page.getHtml().xpath("//div[@class='cnt']/p/span/a/@href").toString().split("=")[1].trim());


            page.putField("cleMusicInfoTemp1", cleMusicInfoTemp1);

        } catch (Exception e) {
            System.out.println("歌曲详细信息XPath解析出现异常" + page.getUrl());
            e.printStackTrace();
            FileUtils.writeStrToFile(page.getUrl() + "\r\n", "G:\\ideawork\\webSpider\\src\\main\\resources\\cle_music_error1.txt");
        }
    }


}

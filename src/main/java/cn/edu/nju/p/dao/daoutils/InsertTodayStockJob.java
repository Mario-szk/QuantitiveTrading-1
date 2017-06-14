package cn.edu.nju.p.dao.daoutils;

import cn.edu.nju.p.dao.StockDao;
import cn.edu.nju.p.dao.daoutils.GetDataFromSinaUtil;
import cn.edu.nju.p.po.StockPO;
import cn.edu.nju.p.utils.beans.ToolSpring;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;


/**
 * 每天定时爬取股票插入数据库
 * 准备用quartz实现
 * Created by dc on 2017/5/14.
 */
@Component
public class InsertTodayStockJob implements Job{

    public InsertTodayStockJob(){

    }

    /**
     * 定时执行的任务
     * @param context
     * @throws JobExecutionException
     */
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
//        long start = System.currentTimeMillis();
        GetDataFromSinaUtil tool=GetDataFromSinaUtil.getInstance();
        int i=0;
        StockDao stockDao = ToolSpring.getBeans(StockDao.class);

        String[] codeList={"000001.sz","000002.sz","000004.sz","000005.sz","000006.sz","000007.sz","000008.sz","000009.sz","000010.sz","000011.sz","000012.sz","000014.sz","000016.sz","000017.sz","000018.sz","000019.sz","000020.sz","000021.sz","000022.sz","000023.sz","000025.sz","000026.sz","000027.sz","000028.sz","000029.sz","000030.sz","000031.sz","000032.sz","000033.sz","000034.sz","000035.sz","000036.sz","000037.sz","000038.sz","000039.sz","000040.sz","000042.sz","000043.sz","000045.sz","000046.sz","000048.sz","000049.sz","000050.sz","000055.sz","000056.sz","000058.sz","000059.sz","000060.sz","000061.sz","000062.sz","000063.sz","000065.sz","000066.sz","000068.sz","000069.sz","000070.sz","000078.sz","000088.sz","000089.sz","000090.sz","000096.sz","000099.sz","000100.sz","000150.sz","000151.sz","000153.sz","000155.sz","000156.sz","000157.sz","000158.sz","000159.sz","000166.sz","000301.sz","000333.sz","000338.sz","000400.sz","000401.sz","000402.sz","000403.sz","000404.sz","000407.sz","000408.sz","000409.sz","000410.sz","000411.sz","000413.sz","000415.sz","000416.sz","000417.sz","000418.sz","000419.sz","000420.sz","000421.sz","000422.sz","000423.sz","000425.sz","000426.sz","000428.sz","000429.sz","000430.sz","000488.sz","000498.sz","000501.sz","000502.sz","000503.sz","000504.sz","000505.sz","000506.sz","000507.sz","000509.sz","000510.sz","000511.sz","000513.sz","000514.sz","000516.sz","000517.sz","000518.sz","000519.sz","000520.sz","000521.sz","000523.sz","000524.sz","000525.sz","000526.sz","000528.sz","000529.sz","000530.sz","000531.sz","000532.sz","000533.sz","000534.sz","000536.sz","000537.sz","000538.sz","000539.sz","000540.sz","000541.sz","000543.sz","000544.sz","000545.sz","000546.sz","000547.sz","000548.sz","000550.sz","000551.sz","000552.sz","000553.sz","000554.sz","000555.sz","000557.sz","000558.sz","000559.sz","000560.sz","000561.sz","000563.sz","000564.sz","000565.sz","000566.sz","000567.sz","000568.sz","000570.sz","000571.sz","000572.sz","000573.sz","000576.sz","000581.sz","000582.sz","000584.sz","000585.sz","000586.sz","000587.sz","000589.sz","000590.sz","000591.sz","000592.sz","000593.sz","000595.sz","000596.sz","000597.sz","000598.sz","000599.sz","000600.sz","000601.sz","000603.sz","000605.sz","000606.sz","000607.sz","000608.sz","000609.sz","000610.sz","000611.sz","000612.sz","000613.sz","000615.sz","000616.sz","000617.sz","000619.sz","000620.sz","000622.sz","000623.sz","000625.sz","000626.sz","000627.sz","000628.sz","000629.sz","000630.sz","000631.sz","000632.sz","000633.sz","000635.sz","000636.sz","000637.sz","000638.sz","000639.sz","000650.sz","000651.sz","000652.sz","000655.sz","000656.sz","000657.sz","000659.sz","000661.sz","000662.sz","000663.sz","000665.sz","000666.sz","000667.sz","000668.sz","000669.sz","000670.sz","000671.sz","000672.sz","000673.sz","000676.sz","000677.sz","000678.sz","000679.sz","000680.sz","000681.sz","000682.sz","000683.sz","000685.sz","000686.sz","000687.sz","000688.sz","000690.sz","000691.sz","000692.sz","000693.sz","000695.sz","000697.sz","000698.sz","000700.sz","000701.sz","000702.sz","000703.sz","000705.sz","000707.sz","000708.sz","000709.sz","000710.sz","000711.sz","000712.sz","000713.sz","000715.sz","000716.sz","000717.sz","000718.sz","000719.sz","000720.sz","000721.sz","000722.sz","000723.sz","000725.sz","000726.sz","000727.sz","000728.sz","000729.sz","000731.sz","000732.sz","000733.sz","000735.sz","000736.sz","000737.sz","000738.sz","000739.sz","000750.sz","000751.sz","000752.sz","000753.sz","000755.sz","000756.sz","000757.sz","000758.sz","000759.sz","000760.sz","000761.sz","000762.sz","000766.sz","000767.sz","000768.sz","000776.sz","000777.sz","000778.sz","000779.sz","000780.sz","000782.sz","000783.sz","000785.sz","000786.sz","000788.sz","000789.sz","000790.sz","000791.sz","000792.sz","000793.sz","000795.sz","000796.sz","000797.sz","000798.sz","000799.sz","000800.sz","000801.sz","000802.sz","000803.sz","000806.sz","000807.sz","000809.sz","000810.sz","000811.sz","000812.sz","000813.sz","000815.sz","000816.sz","000818.sz","000819.sz","000820.sz","000821.sz","000822.sz","000823.sz","000825.sz","000826.sz","000828.sz","000829.sz","000830.sz","000831.sz","000833.sz","000835.sz","000836.sz","000837.sz","000838.sz","000839.sz","000848.sz","000850.sz","000851.sz","000852.sz","000856.sz","000858.sz","000859.sz","000860.sz","000861.sz","000862.sz","000863.sz","000868.sz","000869.sz","000875.sz","000876.sz","000877.sz","000878.sz","000880.sz","000881.sz","000882.sz","000883.sz","000885.sz","000886.sz","000887.sz","000888.sz","000889.sz","000890.sz","000892.sz","000893.sz","000895.sz","000897.sz","000898.sz","000899.sz","000900.sz","000901.sz","000902.sz","000903.sz","000905.sz","000906.sz","000908.sz","000909.sz","000910.sz","000911.sz","000912.sz","000913.sz","000915.sz","000916.sz","000917.sz","000918.sz","000919.sz","000920.sz","000921.sz","000922.sz","000923.sz","000925.sz","000926.sz","000927.sz","000928.sz","000929.sz","000930.sz","000931.sz","000932.sz","000933.sz","000935.sz","000936.sz","000937.sz","000938.sz","000939.sz","000948.sz","000949.sz","000950.sz","000951.sz","000952.sz","000953.sz","000955.sz","000957.sz","000958.sz","000959.sz","000960.sz","000961.sz","000962.sz","000963.sz","000965.sz","000966.sz","000967.sz","000968.sz","000969.sz","000970.sz","000971.sz","000972.sz","000973.sz","000975.sz","000976.sz","000977.sz","000978.sz","000979.sz","000980.sz","000981.sz","000982.sz","000983.sz","000985.sz","000987.sz","000988.sz","000989.sz","000990.sz","000993.sz","000995.sz","000996.sz","000997.sz","000998.sz","000999.sz","001696.sz","001896.sz","001979.sz","002001.sz","002002.sz","002003.sz","002004.sz","002005.sz","002006.sz","002007.sz","002008.sz","002009.sz","002010.sz","002011.sz","002012.sz","002013.sz","002014.sz","002015.sz","002016.sz","002017.sz","002018.sz","002019.sz","002020.sz","002021.sz","002022.sz","002023.sz","002024.sz","002025.sz","002026.sz","002027.sz","002028.sz","002029.sz","002030.sz","002031.sz","002032.sz","002033.sz","002034.sz","002035.sz","002036.sz","002037.sz","002038.sz","002039.sz","002040.sz","002041.sz","002042.sz","002043.sz","002044.sz","002045.sz","002046.sz","002047.sz","002048.sz","002049.sz","002050.sz","002051.sz","002052.sz","002053.sz","002054.sz","002055.sz","002056.sz","002057.sz","002058.sz","002059.sz","002060.sz","002061.sz","002062.sz","002063.sz","002064.sz","002065.sz","002066.sz","002067.sz","002068.sz","002069.sz","002070.sz","002071.sz","002072.sz","002073.sz","002074.sz","002075.sz","002076.sz","002077.sz","002078.sz","002079.sz","002080.sz","002081.sz","002082.sz","002083.sz","002084.sz","002085.sz","002086.sz","002087.sz","002088.sz","002089.sz","002090.sz","002091.sz","002092.sz","002093.sz","002094.sz","002095.sz","002096.sz","002097.sz","002098.sz","002099.sz","002100.sz","002101.sz","002102.sz","002103.sz","002104.sz","002105.sz","002106.sz","002107.sz","002108.sz","002109.sz","002110.sz","002111.sz","002112.sz","002113.sz","002114.sz","002115.sz","002116.sz","002117.sz","002118.sz","002119.sz","002120.sz","002121.sz","002122.sz","002123.sz","002124.sz","002125.sz","002126.sz","002127.sz","002128.sz","002129.sz","002130.sz","002131.sz","002132.sz","002133.sz","002134.sz","002135.sz","002136.sz","002137.sz","002138.sz","002139.sz","002140.sz","002141.sz","002142.sz","002143.sz","002144.sz","002145.sz","002146.sz","002147.sz","002148.sz","002149.sz","002150.sz","002151.sz","002152.sz","002153.sz","002154.sz","002155.sz","002156.sz","002157.sz","002158.sz","002159.sz","002160.sz","002161.sz","002162.sz","002163.sz","002164.sz","002165.sz","002166.sz","002167.sz","002168.sz","002169.sz","002170.sz","002171.sz","002172.sz","002173.sz","002174.sz","002175.sz","002176.sz","002177.sz","002178.sz","002179.sz","002180.sz","002181.sz","002182.sz","002183.sz","002184.sz","002185.sz","002186.sz","002187.sz","002188.sz","002189.sz","002190.sz","002191.sz","002192.sz","002193.sz","002194.sz","002195.sz","002196.sz","002197.sz","002198.sz","002199.sz","002200.sz","002201.sz","002202.sz","002203.sz","002204.sz","002205.sz","002206.sz","002207.sz","002208.sz","002209.sz","002210.sz","002211.sz","002212.sz","002213.sz","002214.sz","002215.sz","002216.sz","002217.sz","002218.sz","002219.sz","002220.sz","002221.sz","002222.sz","002223.sz","002224.sz","002225.sz","002226.sz","002227.sz","002228.sz","002229.sz","002230.sz","002231.sz","002232.sz","002233.sz","002234.sz","002235.sz","002236.sz","002237.sz","002238.sz","002239.sz","002240.sz","002241.sz","002242.sz","002243.sz","002244.sz","002245.sz","002246.sz","002247.sz","002248.sz","002249.sz","002250.sz","002251.sz","002252.sz","002253.sz","002254.sz","002255.sz","002256.sz","002258.sz","002259.sz","002260.sz","002261.sz","002262.sz","002263.sz","002264.sz","002265.sz","002266.sz","002267.sz","002268.sz","002269.sz","002270.sz","002271.sz","002272.sz","002273.sz","002274.sz","002275.sz","002276.sz","002277.sz","002278.sz","002279.sz","002280.sz","002281.sz","002282.sz","002283.sz","002284.sz","002285.sz","002286.sz","002287.sz","002288.sz","002289.sz","002290.sz","002291.sz","002292.sz","002293.sz","002294.sz","002295.sz","002296.sz","002297.sz","002298.sz","002299.sz","002300.sz","002301.sz","002302.sz","002303.sz","002304.sz","002305.sz","002306.sz","002307.sz","002308.sz","002309.sz","002310.sz","002311.sz","002312.sz","002313.sz","002314.sz","002315.sz","002316.sz","002317.sz","002318.sz","002319.sz","002320.sz","002321.sz","002322.sz","002323.sz","002324.sz","002325.sz","002326.sz","002327.sz","002328.sz","002329.sz","002330.sz","002331.sz","002332.sz","002333.sz","002334.sz","002335.sz","002336.sz","002337.sz","002338.sz","002339.sz","002340.sz","002341.sz","002342.sz","002343.sz","002344.sz","002345.sz","002346.sz","002347.sz","002348.sz","002349.sz","002350.sz","002351.sz","002352.sz","002353.sz","002354.sz","002355.sz","002356.sz","002357.sz","002358.sz","002359.sz","002360.sz","002361.sz","002362.sz","002363.sz","002364.sz","002365.sz","002366.sz","002367.sz","002368.sz","002369.sz","002370.sz","002371.sz","002372.sz","002373.sz","002374.sz","002375.sz","002376.sz","002377.sz","002378.sz","002379.sz","002380.sz","002381.sz","002382.sz","002383.sz","002384.sz","002385.sz","002386.sz","002387.sz","002388.sz","002389.sz","002390.sz","002391.sz","002392.sz","002393.sz","002394.sz","002395.sz","002396.sz","002397.sz","002398.sz","002399.sz","002400.sz","002401.sz","002402.sz","002403.sz","002404.sz","002405.sz","002406.sz","002407.sz","002408.sz","002409.sz","002410.sz","002411.sz","002412.sz","002413.sz","002414.sz","002415.sz","002416.sz","002417.sz","002418.sz","002419.sz","002420.sz","002421.sz","002422.sz","002423.sz","002424.sz","002425.sz","002426.sz","002427.sz","002428.sz","002429.sz","002430.sz","002431.sz","002432.sz","002433.sz","002434.sz","002435.sz","002436.sz","002437.sz","002438.sz","002439.sz","002440.sz","002441.sz","002442.sz","002443.sz","002444.sz","002445.sz","002446.sz","002447.sz","002448.sz","002449.sz","002450.sz","002451.sz","002452.sz","002453.sz","002454.sz","002455.sz","002456.sz","002457.sz","002458.sz","002459.sz","002460.sz","002461.sz","002462.sz","002463.sz","002464.sz","002465.sz","002466.sz","002467.sz","002468.sz","002469.sz","002470.sz","002471.sz","002472.sz","002473.sz","002474.sz","002475.sz","002476.sz","002477.sz","002478.sz","002479.sz","002480.sz","002481.sz","002482.sz","002483.sz","002484.sz","002485.sz","002486.sz","002487.sz","002488.sz","002489.sz","002490.sz","002491.sz","002492.sz","002493.sz","002494.sz","002495.sz","002496.sz","002497.sz","002498.sz","002499.sz","002500.sz","002501.sz","002502.sz","002503.sz","002504.sz","002505.sz","002506.sz","002507.sz","002508.sz","002509.sz","002510.sz","002511.sz","002512.sz","002513.sz","002514.sz","002515.sz","002516.sz","002517.sz","002518.sz","002519.sz","002520.sz","002521.sz","002522.sz","002523.sz","002524.sz","002526.sz","002527.sz","002528.sz","002529.sz","002530.sz","002531.sz","002532.sz","002533.sz","002534.sz","002535.sz","002536.sz","002537.sz","002538.sz","002539.sz","002540.sz","002541.sz","002542.sz","002543.sz","002544.sz","002545.sz","002546.sz","002547.sz","002548.sz","002549.sz","002550.sz","002551.sz","002552.sz","002553.sz","002554.sz","002555.sz","002556.sz","002557.sz","002558.sz","002559.sz","002560.sz","002561.sz","002562.sz","002563.sz","002564.sz","002565.sz","002566.sz","002567.sz","002568.sz","002569.sz","002570.sz","002571.sz","002572.sz","002573.sz","002574.sz","002575.sz","002576.sz","002577.sz","002578.sz","002579.sz","002580.sz","002581.sz","002582.sz","002583.sz","002584.sz","002585.sz","002586.sz","002587.sz","002588.sz","002589.sz","002590.sz","002591.sz","002592.sz","002593.sz","002594.sz","002595.sz","002596.sz","002597.sz","002598.sz","002599.sz","002600.sz","002601.sz","002602.sz","002603.sz","002604.sz","002605.sz","002606.sz","002607.sz","002608.sz","002609.sz","002610.sz","002611.sz","002612.sz","002613.sz","002614.sz","002615.sz","002616.sz","002617.sz","002618.sz","002619.sz","002620.sz","002621.sz","002622.sz","002623.sz","002624.sz","002625.sz","002626.sz","002627.sz","002628.sz","002629.sz","002630.sz","002631.sz","002632.sz","002633.sz","002634.sz","002635.sz","002636.sz","002637.sz","002638.sz","002639.sz","002640.sz","002641.sz","002642.sz","002643.sz","002644.sz","002645.sz","002646.sz","002647.sz","002648.sz","002649.sz","002650.sz","002651.sz","002652.sz","002653.sz","002654.sz","002655.sz","002656.sz","002657.sz","002658.sz","002659.sz","002660.sz","002661.sz","002662.sz","002663.sz","002664.sz","002665.sz","002666.sz","002667.sz","002668.sz","002669.sz","002670.sz","002671.sz","002672.sz","002673.sz","002674.sz","002675.sz","002676.sz","002677.sz","002678.sz","002679.sz","002680.sz","002681.sz","002682.sz","002683.sz","002684.sz","002685.sz","002686.sz","002687.sz","002688.sz","002689.sz","002690.sz","002691.sz","002692.sz","002693.sz","002694.sz","002695.sz","002696.sz","002697.sz","002698.sz","002699.sz","002700.sz","002701.sz","002702.sz","002703.sz","002705.sz","002706.sz","002707.sz","002708.sz","002709.sz","002711.sz","002712.sz","002713.sz","002714.sz","002715.sz","002716.sz","002717.sz","002718.sz","002719.sz","002721.sz","002722.sz","002723.sz","002724.sz","002725.sz","002726.sz","002727.sz","002728.sz","002729.sz","002730.sz","002731.sz","002732.sz","002733.sz","002734.sz","002735.sz","002736.sz","002737.sz","002738.sz","002739.sz","002740.sz","002741.sz","002742.sz","002743.sz","002745.sz","002746.sz","002747.sz","002748.sz","002749.sz","002750.sz","002751.sz","002752.sz","002753.sz","002755.sz","002756.sz","002757.sz","002758.sz","002759.sz","002760.sz","002761.sz","002762.sz","002763.sz","002765.sz","002766.sz","002767.sz","002768.sz","002769.sz","002770.sz","002771.sz","002772.sz","002773.sz","002774.sz","002775.sz","002776.sz","002777.sz","002778.sz","002779.sz","002780.sz","002781.sz","002782.sz","002783.sz","002785.sz","002786.sz","002787.sz","002788.sz","002789.sz","002790.sz","002791.sz","002792.sz","002793.sz","002795.sz","002796.sz","002797.sz","002798.sz","002799.sz","002800.sz","002801.sz","002802.sz","002803.sz","002805.sz","002806.sz","002807.sz","002808.sz","002809.sz","002810.sz","002811.sz","002812.sz","002813.sz","002815.sz","002816.sz","002817.sz","002818.sz","002819.sz","002820.sz","002821.sz","002822.sz","002823.sz","002824.sz","002825.sz","002826.sz","002827.sz","002828.sz","002829.sz","002830.sz","002831.sz","002832.sz","002833.sz","002835.sz","002836.sz","002837.sz","002838.sz","002839.sz","002840.sz","002841.sz","002842.sz","002843.sz","002845.sz","002846.sz","002847.sz","002848.sz","002849.sz","002850.sz","002851.sz","002852.sz","002853.sz","002855.sz","002856.sz","002857.sz","002858.sz","002859.sz","002860.sz","002861.sz","002862.sz","002863.sz","002865.sz","002866.sz","002867.sz","002868.sz","002869.sz","002870.sz","002871.sz","002872.sz","002873.sz","002875.sz","002876.sz","002877.sz","002878.sz","300001.sz","300002.sz","300003.sz","300004.sz","300005.sz","300006.sz","300007.sz","300008.sz","300009.sz","300010.sz","300011.sz","300012.sz","300013.sz","300014.sz","300015.sz","300016.sz","300017.sz","300018.sz","300019.sz","300020.sz","300021.sz","300022.sz","300023.sz","300024.sz","300025.sz","300026.sz","300027.sz","300028.sz","300029.sz","300030.sz","300031.sz","300032.sz","300033.sz","300034.sz","300035.sz","300036.sz","300037.sz","300038.sz","300039.sz","300040.sz","300041.sz","300042.sz","300043.sz","300044.sz","300045.sz","300046.sz","300047.sz","300048.sz","300049.sz","300050.sz","300051.sz","300052.sz","300053.sz","300054.sz","300055.sz","300056.sz","300057.sz","300058.sz","300059.sz","300061.sz","300062.sz","300063.sz","300064.sz","300065.sz","300066.sz","300067.sz","300068.sz","300069.sz","300070.sz","300071.sz","300072.sz","300073.sz","300074.sz","300075.sz","300076.sz","300077.sz","300078.sz","300079.sz","300080.sz","300081.sz","300082.sz","300083.sz","300084.sz","300085.sz","300086.sz","300087.sz","300088.sz","300089.sz","300090.sz","300091.sz","300092.sz","300093.sz","300094.sz","300095.sz","300096.sz","300097.sz","300098.sz","300099.sz","300100.sz","300101.sz","300102.sz","300103.sz","300104.sz","300105.sz","300106.sz","300107.sz","300108.sz","300109.sz","300110.sz","300111.sz","300112.sz","300113.sz","300114.sz","300115.sz","300116.sz","300117.sz","300118.sz","300119.sz","300120.sz","300121.sz","300122.sz","300123.sz","300124.sz","300125.sz","300126.sz","300127.sz","300128.sz","300129.sz","300130.sz","300131.sz","300132.sz","300133.sz","300134.sz","300135.sz","300136.sz","300137.sz","300138.sz","300139.sz","300140.sz","300141.sz","300142.sz","300143.sz","300144.sz","300145.sz","300146.sz","300147.sz","300148.sz","300149.sz","300150.sz","300151.sz","300152.sz","300153.sz","300154.sz","300155.sz","300156.sz","300157.sz","300158.sz","300159.sz","300160.sz","300161.sz","300162.sz","300163.sz","300164.sz","300165.sz","300166.sz","300167.sz","300168.sz","300169.sz","300170.sz","300171.sz","300172.sz","300173.sz","300174.sz","300175.sz","300176.sz","300177.sz","300178.sz","300179.sz","300180.sz","300181.sz","300182.sz","300183.sz","300184.sz","300185.sz","300187.sz","300188.sz","300189.sz","300190.sz","300191.sz","300192.sz","300193.sz","300194.sz","300195.sz","300196.sz","300197.sz","300198.sz","300199.sz","300200.sz","300201.sz","300202.sz","300203.sz","300204.sz","300205.sz","300206.sz","300207.sz","300208.sz","300209.sz","300210.sz","300211.sz","300212.sz","300213.sz","300214.sz","300215.sz","300216.sz","300217.sz","300218.sz","300219.sz","300220.sz","300221.sz","300222.sz","300223.sz","300224.sz","300225.sz","300226.sz","300227.sz","300228.sz","300229.sz","300230.sz","300231.sz","300232.sz","300233.sz","300234.sz","300235.sz","300236.sz","300237.sz","300238.sz","300239.sz","300240.sz","300241.sz","300242.sz","300243.sz","300244.sz","300245.sz","300246.sz","300247.sz","300248.sz","300249.sz","300250.sz","300251.sz","300252.sz","300253.sz","300254.sz","300255.sz","300256.sz","300257.sz","300258.sz","300259.sz","300260.sz","300261.sz","300262.sz","300263.sz","300264.sz","300265.sz","300266.sz","300267.sz","300268.sz","300269.sz","300270.sz","300271.sz","300272.sz","300273.sz","300274.sz","300275.sz","300276.sz","300277.sz","300278.sz","300279.sz","300280.sz","300281.sz","300282.sz","300283.sz","300284.sz","300285.sz","300286.sz","300287.sz","300288.sz","300289.sz","300290.sz","300291.sz","300292.sz","300293.sz","300294.sz","300295.sz","300296.sz","300297.sz","300298.sz","300299.sz","300300.sz","300301.sz","300302.sz","300303.sz","300304.sz","300305.sz","300306.sz","300307.sz","300308.sz","300309.sz","300310.sz","300311.sz","300312.sz","300313.sz","300314.sz","300315.sz","300316.sz","300317.sz","300318.sz","300319.sz","300320.sz","300321.sz","300322.sz","300323.sz","300324.sz","300325.sz","300326.sz","300327.sz","300328.sz","300329.sz","300330.sz","300331.sz","300332.sz","300333.sz","300334.sz","300335.sz","300336.sz","300337.sz","300338.sz","300339.sz","300340.sz","300341.sz","300342.sz","300343.sz","300344.sz","300345.sz","300346.sz","300347.sz","300348.sz","300349.sz","300350.sz","300351.sz","300352.sz","300353.sz","300354.sz","300355.sz","300356.sz","300357.sz","300358.sz","300359.sz","300360.sz","300362.sz","300363.sz","300364.sz","300365.sz","300366.sz","300367.sz","300368.sz","300369.sz","300370.sz","300371.sz","300372.sz","300373.sz","300374.sz","300375.sz","300376.sz","300377.sz","300378.sz","300379.sz","300380.sz","300381.sz","300382.sz","300383.sz","300384.sz","300385.sz","300386.sz","300387.sz","300388.sz","300389.sz","300390.sz","300391.sz","300392.sz","300393.sz","300394.sz","300395.sz","300396.sz","300397.sz","300398.sz","300399.sz","300400.sz","300401.sz","300402.sz","300403.sz","300404.sz","300405.sz","300406.sz","300407.sz","300408.sz","300409.sz","300410.sz","300411.sz","300412.sz","300413.sz","300414.sz","300415.sz","300416.sz","300417.sz","300418.sz","300419.sz","300420.sz","300421.sz","300422.sz","300423.sz","300424.sz","300425.sz","300426.sz","300427.sz","300428.sz","300429.sz","300430.sz","300431.sz","300432.sz","300433.sz","300434.sz","300435.sz","300436.sz","300437.sz","300438.sz","300439.sz","300440.sz","300441.sz","300442.sz","300443.sz","300444.sz","300445.sz","300446.sz","300447.sz","300448.sz","300449.sz","300450.sz","300451.sz","300452.sz","300453.sz","300455.sz","300456.sz","300457.sz","300458.sz","300459.sz","300460.sz","300461.sz","300462.sz","300463.sz","300464.sz","300465.sz","300466.sz","300467.sz","300468.sz","300469.sz","300470.sz","300471.sz","300472.sz","300473.sz","300474.sz","300475.sz","300476.sz","300477.sz","300478.sz","300479.sz","300480.sz","300481.sz","300482.sz","300483.sz","300484.sz","300485.sz","300486.sz","300487.sz","300488.sz","300489.sz","300490.sz","300491.sz","300492.sz","300493.sz","300494.sz","300495.sz","300496.sz","300497.sz","300498.sz","300499.sz","300500.sz","300501.sz","300502.sz","300503.sz","300505.sz","300506.sz","300507.sz","300508.sz","300509.sz","300510.sz","300511.sz","300512.sz","300513.sz","300514.sz","300515.sz","300516.sz","300517.sz","300518.sz","300519.sz","300520.sz","300521.sz","300522.sz","300523.sz","300525.sz","300526.sz","300527.sz","300528.sz","300529.sz","300530.sz","300531.sz","300532.sz","300533.sz","300534.sz","300535.sz","300536.sz","300537.sz","300538.sz","300539.sz","300540.sz","300541.sz","300542.sz","300543.sz","300545.sz","300546.sz","300547.sz","300548.sz","300549.sz","300550.sz","300551.sz","300552.sz","300553.sz","300554.sz","300555.sz","300556.sz","300557.sz","300558.sz","300559.sz","300560.sz","300561.sz","300562.sz","300563.sz","300565.sz","300566.sz","300567.sz","300568.sz","300569.sz","300570.sz","300571.sz","300572.sz","300573.sz","300575.sz","300576.sz","300577.sz","300578.sz","300579.sz","300580.sz","300581.sz","300582.sz","300583.sz","300584.sz","300585.sz","300586.sz","300587.sz","300588.sz","300589.sz","300590.sz","300591.sz","300592.sz","300593.sz","300595.sz","300596.sz","300597.sz","300598.sz","300599.sz","300600.sz","300601.sz","300602.sz","300603.sz","300604.sz","300605.sz","300606.sz","300607.sz","300608.sz","300609.sz","300610.sz","300611.sz","300612.sz","300613.sz","300615.sz","300616.sz","300617.sz","300618.sz","300619.sz","300620.sz","300621.sz","300622.sz","300623.sz","300625.sz","300626.sz","300627.sz","300628.sz","300629.sz","300630.sz","300631.sz","300632.sz","300633.sz","300635.sz","300636.sz","300637.sz","300638.sz","300639.sz","300640.sz","300641.sz","300642.sz","300643.sz","300645.sz","300647.sz","300648.sz","300649.sz","300650.sz","300651.sz","300652.sz","300653.sz","300655.sz","300656.sz","300657.sz","300658.sz","300659.sz","300660.sz","300661.sz","300662.sz","300663.sz"};

        List<StockPO> poList = GetDataFromSinaUtil.getInstance().getPOList(codeList);
        System.out.println("获取数据完毕");
        System.out.println("开始插入数据库");
        for (StockPO po : poList) {
            try {
                stockDao.insertIntoStockDatabase("2017", po);
                i++;
                // 当前线程等待3秒
                Thread.sleep(30L * 100L);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("共插入" + i + "支股票");

//        long end=System.currentTimeMillis();
//        System.out.println((end - start)/1000);//换算成秒钟 1s=1000ms

    }
}
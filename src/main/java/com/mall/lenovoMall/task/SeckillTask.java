package com.mall.lenovoMall.task;

import com.mall.lenovoMall.entity.Product;
import com.mall.lenovoMall.mapper.ProductMapper;
import com.mall.lenovoMall.mapper.SeckillProductMapper;
import com.mall.lenovoMall.mapper.SeckillTimeMapper;
import com.mall.lenovoMall.entity.SeckillProduct;
import com.mall.lenovoMall.entity.SeckillTime;
import com.mall.lenovoMall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class SeckillTask {

    @Autowired
    private SeckillTimeMapper seckillTimeMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private SeckillProductMapper seckillProductMapper;

    /**
     * @Scheduled 定时任务
     * 
     */
    @Scheduled(cron = "0 45 23 * * ?")
    public void execute() {
        // 获取商品设为秒杀商品
        List<Integer> productIds = productMapper.selectIds();
        Date time = getDate();
        seckillTimeMapper.deleteAll();
        seckillProductMapper.deleteAll();
        for (int i = 1; i < 24; i = i + 2) {

            // 插入时间
            long startTime = time.getTime()/1000*1000 + 1000 * 60 * 60 * i;
            long endTime = startTime + 1000 * 60 * 60;
            SeckillTime seckillTime = new SeckillTime();
            seckillTime.setStartTime(startTime);
            seckillTime.setEndTime(endTime);
            seckillTimeMapper.insert(seckillTime);


            // 随机选15个商品id
            HashSet<Integer> set = new HashSet<>();
            while (set.size() < 15) {
                Random random = new Random();
                int nextInt = random.nextInt(productIds.size());
                set.add(productIds.get(nextInt));
            }

            ArrayList<Integer> integers = new ArrayList<>(set);

            System.out.println(integers);

            // 添加秒杀商品
            ArrayList<SeckillProduct> seckillProducts = new ArrayList<>();
            for (int j = 0; j < 15; j++) {
                SeckillProduct seckillProduct = new SeckillProduct();
                Product product= productMapper.getProductById(integers.get(j));
                seckillProduct.setSeckillPrice(product.getProductPrice()*0.5);
                seckillProduct.setSeckillStock((int) (product.getProductNum()*0.05));
//                seckillProduct.setSeckillPrice(1000.0);
//                seckillProduct.setSeckillStock(100);
                seckillProduct.setProductId(integers.get(j));
                seckillProduct.setTimeId(seckillTime.getTimeId());
                seckillProducts.add(seckillProduct);
                System.out.println(seckillProduct);
            }

            seckillProductMapper.insertList(seckillProducts);
            // System.out.println(Arrays.toString(seckillProducts.toArray()));

            try {
                Thread.sleep(1_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("完成---------------------");

        }

        System.out.println("一次添加ok-------------------------------------------");
        // try {
        //     Thread.sleep(100_000);
        // } catch (InterruptedException e) {
        //     e.printStackTrace();
        // }

    }

    private Date getDate() {
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        return ca.getTime();
    }

}

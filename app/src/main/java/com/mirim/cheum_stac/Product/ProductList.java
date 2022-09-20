package com.mirim.cheum_stac.Product;

import com.mirim.cheum_stac.R;

import java.util.List;

public class ProductList {

    public static List<Product> productList =
        List.of(
                new Product(0, "hair", R.drawable.product_img, "BEST [에디터픽]", "샴푸1", "900원"),
                new Product(1, "hair", R.drawable.product_img, "BEST [에디터픽]", "린스", "900원"),
                new Product(2,"hair", R.drawable.product_img, "", "샴푸2", "900원"),
                new Product(3,"body", R.drawable.product_img, "BEST [에디터픽]", "바디워시1", "900원"),
                new Product(4,"body", R.drawable.product_img, "BEST [에디터픽]", "바디워시2", "900원"),
                new Product(5,"body", R.drawable.product_img, "", "비누", "900원"),
                new Product(6,"cos", R.drawable.product_img, "", "틴트", "900원"),
                new Product(7,"cos", R.drawable.product_img, "BEST [에디터픽]", "립밤", "900원"),
                new Product(8,"cos", R.drawable.product_img, "", "파운데이션", "900원"),
                new Product(9,"laund", R.drawable.product_img, "BEST [에디터픽]", "세제1", "900원"),
                new Product(10,"laund", R.drawable.product_img, "BEST [에디터픽]", "세제2", "900원"),
                new Product(11,"laund", R.drawable.product_img, "", "세제3", "900원"),
                new Product(12,"kit", R.drawable.product_img, "", "세제4", "900원"),
                new Product(13,"kit",R.drawable.product_img, "BEST [에디터픽]", "세제5", "900원"),
                new Product(14,"kit", R.drawable.product_img, "BEST [에디터픽]", "세제6", "900원"),
                new Product(15,"food", R.drawable.product_img, "BEST [에디터픽]", "음료수1", "900원"),
                new Product(16,"food",R.drawable.product_img, "", "음료수2", "900원"),
                new Product(17,"food", R.drawable.product_img, "", "음료수3", "900원")
        );
}

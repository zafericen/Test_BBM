import React, {Dispatch, SetStateAction} from "react";
import styles from "./index.module.scss";
import ProductCard from "../../product/product-card-user";
import ProductCardOther from "../../product/product-card-other";

function MerchantProducts({userId, pageFunc, productFunc} : {userId: String, pageFunc: Dispatch<SetStateAction<string>>, productFunc: Dispatch<SetStateAction<string>>}) {
    //TODO: Fetch products from backend
  return (
      <div className={styles['general-container']}>
          <div className={styles['product-header']}>
              <h1 className={styles['title']}>Your Products</h1>
              <button onClick={() => pageFunc("add-product")} className={styles["add-product-button"]}>
                  <div className={styles['button-text']}>Add Product</div>
                  <img src="/plus.png" alt="Add Product" className={styles['plus-icon']}/>
              </button>
          </div>
          <div className={styles["merchant-product-container"]}>
              <ProductCardOther productId="1" pageFunc={pageFunc} productFunc={productFunc}/>
              <ProductCardOther productId="2" pageFunc={pageFunc} productFunc={productFunc}/>
              <ProductCardOther productId="3" pageFunc={pageFunc} productFunc={productFunc}/>
              <ProductCardOther productId="4" pageFunc={pageFunc} productFunc={productFunc}/>
              <ProductCardOther productId="5" pageFunc={pageFunc} productFunc={productFunc}/>
              <ProductCardOther productId="6" pageFunc={pageFunc} productFunc={productFunc}/>
              <ProductCardOther productId="7" pageFunc={pageFunc} productFunc={productFunc}/>
              <ProductCardOther productId="8" pageFunc={pageFunc} productFunc={productFunc}/>
          </div>
      </div>
  );
}

export default MerchantProducts;
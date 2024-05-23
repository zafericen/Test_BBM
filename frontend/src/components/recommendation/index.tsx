import styles from './index.module.scss'
import ProductCard from "../product/product-card-user";
import {Dispatch, SetStateAction, useState} from "react";

function Recommendation({userId, pageFunc, productFunc} : {userId: String, pageFunc: Dispatch<SetStateAction<string>>, productFunc: Dispatch<SetStateAction<string>>}) {

    const [selection, setSelection] = useState(0);

    return (
        <div className={styles['general-container']}>
            <div className={styles['button-container']}>
                <button className={selection === 0 ? styles['button-selected'] : styles['button-not-selected']}>
                    <div className={styles['button-text']}>For You</div>
                    <img src={"/for-you-icon.png"} className={styles["button-icon"]}/>
                </button>
                <button className={selection === 1 ? styles['button-selected'] : styles['button-not-selected']}>
                    <div className={styles['button-text']}>Most Viewed Products</div>
                    <img src={"/eye.png"} className={styles["eye-icon"]}/>
                </button>
                <button className={selection === 2 ? styles['button-selected'] : styles['button-not-selected']}>
                    <div className={styles['button-text']}>Most Favourited Products</div>
                    <img src={"/favorites.png"} className={styles["button-icon"]}/>
                </button>
                <button className={selection === 3 ? styles['button-selected'] : styles['button-not-selected']}>
                    <div className={styles['button-text']}>Communities</div>
                    <img src={"/community.png"} className={styles["button-icon"]}/>
                </button>
            </div>
            <div className={styles["recommendation-container"]}>
                <ProductCard productId="1" pageFunc={pageFunc} productFunc={productFunc}/>
                <ProductCard productId="2" pageFunc={pageFunc} productFunc={productFunc}/>
                <ProductCard productId="3" pageFunc={pageFunc} productFunc={productFunc}/>
                <ProductCard productId="4" pageFunc={pageFunc} productFunc={productFunc}/>
                <ProductCard productId="5" pageFunc={pageFunc} productFunc={productFunc}/>
                <ProductCard productId="6" pageFunc={pageFunc} productFunc={productFunc}/>
                <ProductCard productId="7" pageFunc={pageFunc} productFunc={productFunc}/>
                <ProductCard productId="8" pageFunc={pageFunc} productFunc={productFunc}/>
            </div>
        </div>
    )
}

export default Recommendation;
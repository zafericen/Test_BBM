import React, {Dispatch, SetStateAction} from "react";
import styles from "./index.module.scss";
import MerchantDashboardPage from "../merchant-dashboard-page";
import AddProduct from "../../components/product/add-product";

function AddProductPage({userId, pageFunc, productFunc} : {userId: String, pageFunc: Dispatch<SetStateAction<string>>, productFunc: Dispatch<SetStateAction<string>>}) {
    return (
        <div className={styles['app-root']}>
            <div className={styles['background']}>
                <MerchantDashboardPage userId={userId} pageFunc={pageFunc} productFunc={productFunc} />
            </div>
            <div className={styles['foreground']}>
                <AddProduct userId={userId} pageFunc={pageFunc} productFunc={productFunc} />
            </div>
        </div>
    );
}

export default AddProductPage;
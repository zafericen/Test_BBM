import React, {Dispatch, SetStateAction} from "react";
import styles from "./index.module.scss";
import MerchantDashboardPage from "../merchant-dashboard-page";
import AddProduct from "../../components/product/add-product";
import EditProduct from "../../components/product/edit-product";

function EditProductPage({userId, pageFunc, productFunc} : {userId: String, pageFunc: Dispatch<SetStateAction<string>>, productFunc: Dispatch<SetStateAction<string>>}) {
    return (
        <div className={styles['app-root']}>
            <div className={styles['background']}>
                <MerchantDashboardPage userId={userId} pageFunc={pageFunc} productFunc={productFunc} />
            </div>
            <div className={styles['foreground']}>
                <EditProduct userId={userId} pageFunc={pageFunc} productFunc={productFunc} />
            </div>
        </div>
    );
}

export default EditProductPage;
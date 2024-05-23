import React, {Dispatch, SetStateAction} from "react";
import styles from "../add-product-page/index.module.scss";
import ProductPage from "../product-page";
import EditComment from "../../components/comment/add-comment";

function EditCommentPage({userId, productId, pageFunc} : {userId: String, productId: String, pageFunc: Dispatch<SetStateAction<string>>}) {
    return (
        <div className={styles['app-root']}>
            <div className={styles['background']}>
                <ProductPage productId={productId} pageFunc={pageFunc} />
            </div>
            <div className={styles['foreground']}>
                <EditComment userId={userId} pageFunc={pageFunc}/>
            </div>
        </div>
    );
}

export default EditCommentPage;
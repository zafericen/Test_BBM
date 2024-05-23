import React, {Dispatch, SetStateAction} from "react";
import styles from "./index.module.scss";

import MerchantProducts from "./merchant-products";
import MerchantQuestions from "./merchant-questions";
function MerchantDashboard({userId, pageFunc, productFunc} : {userId: String, pageFunc: Dispatch<SetStateAction<string>>, productFunc: Dispatch<SetStateAction<string>>}) {
    return (
        <div className={styles["app-body"]}>
            <MerchantProducts userId={userId} pageFunc={pageFunc} productFunc={productFunc}/>
            <MerchantQuestions userId={userId} pageFunc={pageFunc}/>
        </div>
    );
}

export default MerchantDashboard;
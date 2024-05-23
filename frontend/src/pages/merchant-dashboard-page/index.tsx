import React, {Dispatch, SetStateAction} from "react";
import styles from "./index.module.scss";

import MerchantDashboard from "../../components/merchant-dashboard";

function MerchantDashboardPage({userId, pageFunc, productFunc} : {userId: String, pageFunc: Dispatch<SetStateAction<string>>, productFunc: Dispatch<SetStateAction<string>>}) {
  return (
        <div>
            <div className={styles["app-body"]}>
                <MerchantDashboard userId={userId} pageFunc={pageFunc} productFunc={productFunc}/>
            </div>
        </div>
  );
}

export default MerchantDashboardPage;
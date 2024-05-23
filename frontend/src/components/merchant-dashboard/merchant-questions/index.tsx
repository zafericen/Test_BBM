import React, {Dispatch, SetStateAction} from "react";
import styles from "./index.module.scss";

function MerchantQuestions({userId, pageFunc} : {userId: String, pageFunc: Dispatch<SetStateAction<string>>}) {
  //TODO: Fetch questions from backend
    return (
          <div className={styles["general-container"]}>
              <div className={styles["questions-title"]}>Questions</div>
              <div className={styles["questions-container"]}>
                  <div className={styles["question"]} onClick={() => pageFunc("question")} >
                      <div className={styles["question-header"]}>
                          <div className={styles["question-product"]}>product name</div>
                          <button className={styles["answer-button"]}>
                              <img src={"/edit.png"} className={styles['answer-icon']}/>
                          </button>
                      </div>
                      <div className={styles["question-content"]}>question content</div>
                  </div>
              </div>
          </div>
    );
}

export default MerchantQuestions;
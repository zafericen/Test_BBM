import React, {Dispatch, SetStateAction} from "react";
import styles from "./index.module.scss";
import HeaderOther from "../../header-footer/header/header-other";
import Image from "../../image";

function AddProduct({userId, pageFunc, productFunc} : {userId: String, pageFunc: Dispatch<SetStateAction<string>>, productFunc: Dispatch<SetStateAction<string>>}) {
    //TODO: Take images and categories from the backend
    return (
          <div className={styles['general-container']}>
              <div className={styles['name-and-price']}>
                  <input type="text" className={styles['name-box']} placeholder={"Enter New Name"}/>
                  <input type="text" className={styles['price-box']} placeholder={"Enter New Price"}/>
              </div>
              <textarea className={styles['description-box']} placeholder={'Enter New Description'}/>
              <div className={styles['images-container']}>
                  <Image image={'/image1.jpg'}/>
                  <Image image={'/image2.jpg'}/>
                  <Image image={'/image3.jpg'}/>
                  <Image image={'/image4.jpg'}/>
                  <Image image={'/image5.jpg'}/>
              </div>
              <div className={styles['buttons-container']}>
                  <select className={styles['select-category']}>
                      <optgroup>
                          <option value="1">Category 1</option>
                          <option value="2">Category 2</option>
                          <option value="3">Category 3</option>
                          <option value="4">Category 4</option>
                      </optgroup>
                  </select>
                  <button onClick={() => {pageFunc("merchant-dashboard");}} className={styles['cancel-button']}>
                        <div className={styles['button-text']}>Cancel</div>
                      <img className={styles['icon']} src="/trashcan.png" alt="Upload Image"/>
                  </button>
                  <button className={styles['upload-image-button']}>
                      <div className={styles['button-text']}>Upload Image</div>
                      <img className={styles['icon']} src="/attachment.png" alt="Upload Image"/>
                  </button>
                  <button className={styles['submit-button']}>
                      <div className={styles['button-text']}>Submit</div>
                  </button>
              </div>
          </div>
  );
}

export default AddProduct;
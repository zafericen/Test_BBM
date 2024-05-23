import styles from "./index.module.scss";
import {Dispatch, SetStateAction} from "react";

function getStars(rating : number) {
    let stars: string[] = [];
    while (rating >= 1) {
        stars.push("/star-full.png");
        rating = rating - 1;
    }

    if (rating >= 0.5) {
        stars.push("/star-half.png");
    }

    while (stars.length < 5) {
        stars.push("/star-empty.png");
    }

    return stars;
}

function getStarsDiv(stars: string[]) {
    let divs: JSX.Element[] = [];

    for (let star of stars) {
        divs.push(<img src={star} className={styles["star-icon"]} />)
    }

    return divs;
}

function ProductCardOther({productId, pageFunc, productFunc} : {productId: String, pageFunc: Dispatch<SetStateAction<string>>, productFunc: Dispatch<SetStateAction<string>>}) {
    //TODO: Draw from backend.

    const image = "/attachment.png";
    const productName = "<product name>";
    const price = "<price>";
    const merchantName = "<merchant name>";
    const rating = 3.5;
    const isLiked = true;

    return (
        <div className={styles.container}>
            <img src={image} className={styles.image} onClick={() => {
                pageFunc("product");
                productFunc(productId + "");
            }}/>
            <div className={styles["info-container"]}>
                <div className={styles["text-big"]}>{productName}</div>
                <div className={styles.text}>{price}</div>
                <div className={styles.text}>{merchantName}</div>
                <div className={styles["stars-and-edit"]}>
                    <div>{rating}</div>
                    <div className={styles.stars}>
                        {getStarsDiv(getStars(rating))}
                    </div>
                    <button onClick={() => {pageFunc("edit-product"); productFunc(productId + "")}}
                            className={styles['edit-button']}>
                        <img className={styles['edit-icon']} src="/edit.png" alt="Edit Product"/>
                    </button>
                </div>
            </div>
        </div>
    )
}

export default ProductCardOther;
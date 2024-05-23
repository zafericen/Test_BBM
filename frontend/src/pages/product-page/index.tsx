import styles from "./index.module.scss";
import CommentContainer from "../../components/comment/comment-container";
import {Dispatch, SetStateAction} from "react";

function getImages(productId: String) {
    let images: string[] = ['attachment.png', 'attachment.png', 'attachment.png', "attachment.png", "attachment.png"];
    //TODO: Get images from backend.
    return images;
}

function getStars(rating: number) {
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
        divs.push(<img src={star} className={styles["star-icon"]}/>)
    }

    return divs;
}

function ProductPage({productId, pageFunc}: { productId: String , pageFunc: Dispatch<SetStateAction<string>>}) {
    //TODO: Draw from backend.

    const images = getImages(productId);
    const productName = "<Product Name>";
    const description = "Dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero. Sed cursus ante dapibus diam. Sed nisi. Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum. Praesent mauris. Fusce nec tellus sed augue semper porta. Mauris massa.";
    const price = "<price>";
    const merchantName = "<Merchant Name>";
    const rating = 3.7;
    const isLiked = true;

    const userName = "<Username>";
    const userRating = 3.5;
    const commentDate = '01.01.2024'
    const userComment = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero. Sed cursus ante dapibus diam. Sed nisi. Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum. Praesent mauris. Fusce nec tellus sed augue semper porta. Mauris massa. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio.";

    return (
        <div className={styles['general-container']}>
            <div className={styles['product-container']}>
                <div className={styles["left-container"]}>
                    <img src={images[0]} className={styles.image}/>
                    <div className={styles["small-image-container"]}>
                        {images.map((image) => {
                            return <img src={image} className={styles["small-image"]}/>
                        })}
                    </div>
                </div>
                <div className={styles["right-container"]}>
                    <div className={styles["info-container"]}>
                        <div className={styles["title"]}>{productName}</div>
                        <div className={styles["price-and-rating"]}>
                            <div className={styles["price-text"]}>{price}</div>
                            <div className={styles["rating"]}>
                                <div className={styles["rating-text"]}>{rating}</div>
                                <div className={styles["stars"]}>{getStarsDiv(getStars(rating))} </div>
                            </div>
                        </div>
                        <div className={styles.merchant}>
                            {merchantName}
                        </div>
                        <div className={styles['desc-buttons']}>
                            <button className={styles["favorite-button"]}>
                                <img src={isLiked ? "/heart-full.png" : "/favorites.png"}
                                     className={styles["heart-icon"]}/>
                                <div
                                    className={styles['desc-button-text']}>{isLiked ? "Remove From Favorites" : "Add to Favorites"}</div>
                            </button>
                            <button className={styles["question-button"]}>
                                <img src={"/questionmark.png"} className={styles["question-icon"]}/>
                                <div className={styles['desc-button-text']}>Ask a Question</div>
                            </button>
                        </div>
                        <div className={styles['desc-text-container']}>
                            <div className={styles['description-title']}>Product Description</div>
                            <div className={styles['description-text']}>{description}</div>
                        </div>
                    </div>
                </div>
            </div>
            TODO: Add Questions Part
            <button onClick={() => pageFunc("add-comment")} className={styles["add-comment-button"]}>
                <div className={styles['add-comment-button-text']}>Add Your Comment</div>
                <img src={"/plus.png"} className={styles["plus-icon"]}/>
            </button>
            <CommentContainer productId={productId}></CommentContainer>
        </div>
    )
}

export default ProductPage;
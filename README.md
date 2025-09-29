# Bakery APP — Android App

An **Android application** developed as a **university projec** to simulate an online bakery store.  
The app provides features like browsing products, managing a cart, placing orders, and handling account details.

---

## 📌 Features

- **User Authentication**
  - Sign Up & Sign In screens
  - Account management (`AccountFragment`)
- **Product Management**
  - Product listing with categories (`HomeFragment`, `CategoryListAdapter`)
  - Product search functionality (`SearchActivity`, `SearchFragment`)
- **Cart & Checkout**
  - Add products to cart (`CartFragment`)
  - Update cart items with quantity selector (`CartAdapter`)
  - Checkout process (`Checkout`)
- **Orders**
  - Place new orders
  - View order history (`OrdersFragment`, `OrdersAdapter`)
- **Maps Integration**
  - Location support for bakery / delivery address (`Maps`)
- **UI**
  - Bottom navigation bar
  - Custom layouts and icons
  - Rounded image transformation (`ImageCornerTransform`)

---

## 🛠️ Tech Stack

- **Language:** Java  
- **Frameworks & Tools:** Android SDK, Google Play Services (Maps & Auth)  
- **Architecture:** Fragment-based navigation with Activities  
- **Other:**  
  - Custom thread pool for background tasks (`ApplicationThreadPool`)  
  - Adapters for RecyclerViews (products, categories, orders, cart items)  

---

## 📂 Project Structure

```
ccbarkery2/
│── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/finalproject/ccbarkery/
│   │   │   │   ├── MainActivity.java
│   │   │   │   ├── SignIn.java / SignUp.java
│   │   │   │   ├── Fragments/
│   │   │   │   │   ├── HomeFragment.java
│   │   │   │   │   ├── CartFragment.java
│   │   │   │   │   ├── OrdersFragment.java
│   │   │   │   │   └── AccountFragment.java
│   │   │   │   ├── Activities/
│   │   │   │   │   ├── ProductActivity.java
│   │   │   │   │   ├── SearchActivity.java
│   │   │   │   │   └── Checkout.java
│   │   │   │   ├── dataModels/ (CartItem, Order, Products, Categories…)
│   │   │   │   ├── viewAdapters/ (RecyclerView adapters)
│   │   │   │   ├── Services/ (OrderStatusListener)
│   │   │   │   └── Utils/ (Image transforms, thread pool)
│   │   │   └── res/ (layouts, drawables, icons)
│   │   └── AndroidManifest.xml
│   ├── build.gradle
│   └── google-services.json
```

---

## 🚀 Getting Started

### Prerequisites
- Android Studio (**Arctic Fox or newer**)  
- JDK 8+  
- Android device or emulator (API level 21+)  

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/jgsjoel/android.git
   cd android/ccbarkery2
   ```
2. Open the project in **Android Studio**  
3. Sync Gradle and install dependencies  
4. Run on an emulator or physical device  

---

## 📸 Screenshots

<img width="975" height="548" alt="image" src="https://github.com/user-attachments/assets/6e7e98e7-6e04-4a33-9407-371e3568b480" />

<img width="975" height="548" alt="image" src="https://github.com/user-attachments/assets/da3711e5-771d-465c-8e95-67cd82cf168d" />

<img width="975" height="548" alt="image" src="https://github.com/user-attachments/assets/c9c8fcec-505a-4a67-b342-73f15116c5a8" />

<img width="975" height="548" alt="image" src="https://github.com/user-attachments/assets/34e2a99a-dee1-46b1-8fda-96bf69dc24ee" />




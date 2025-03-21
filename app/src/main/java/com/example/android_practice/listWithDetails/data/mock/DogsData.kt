package com.example.android_practice.listWithDetails.data.mock

import com.example.android_practice.listWithDetails.domain.entity.DogFullEntity
//import com.example.android_practice.listWithDetails.domain.entity.DogPicture
import com.example.android_practice.listWithDetails.domain.entity.DogShortEntity

object DogsData {
    val dogsShortEntity = listOf(
        DogShortEntity(
            id = "1",
            name = "Affenpinscher",
            temperament = "Stubborn, Curious, Playful, Adventurous, Active, Fun-loving",
            image = "https://cdn2.thedogapi.com/images/BJa4kxc4X.jpg",
        ),
        DogShortEntity(
            id = "2",
            name = "Afghan Hound",
            temperament = "Aloof, Clownish, Dignified, Independent, Happy",
            image = "https://cdn2.thedogapi.com/images/hMyT4CDXR.jpg",
        ),
        DogShortEntity(
            id = "3",
            name = "African Hunting Dog",
            temperament = "Wild, Hardworking, Dutiful",
            image = "https://cdn2.thedogapi.com/images/rkiByec47.jpg",
        ),
        DogShortEntity(
            id = "4",
            name = "Airedale Terrier",
            temperament = "Outgoing, Friendly, Alert, Confident, Intelligent, Courageous",
            image = "https://cdn2.thedogapi.com/images/1-7cgoZSh.jpg",
        ),
        DogShortEntity(
            id = "5",
            name = "Akbash Dog",
            temperament = "Loyal, Independent, Intelligent, Brave",
            image = "https://cdn2.thedogapi.com/images/26pHT3Qk7.jpg",

        ),

        DogShortEntity(
            id = "6",
            name = "Akita",
            temperament = "Docile, Alert, Responsive, Dignified, Composed, Friendly, Receptive, Faithful, Courageous",
            image = "https://cdn2.thedogapi.com/images/BFRYBufpm.jpg",
        ),

        DogShortEntity(
            id = "7",
            name = "Alapaha Blue Blood Bulldog",
            temperament = "Loving, Protective, Trainable, Dutiful, Responsible",
            image = "https://cdn2.thedogapi.com/images/33mJ-V3RX.jpg",
        ),

        DogShortEntity(
            id = "8",
            name = "Alaskan Husky",
            temperament = "Friendly, Energetic, Loyal, Gentle, Confident",
            image = "https://cdn2.thedogapi.com/images/-HgpNnGXl.jpg",
        ),
    )

    val dogsFullEntity = listOf(
        DogFullEntity(
            id = "1",
            name = "Affenpinscher",
            origin = "Germany, France",
            temperament = "Stubborn, Curious, Playful, Adventurous, Active, Fun-loving",
            lifeSpan = "10 - 12 years",
            breedGroup = "Toy",
            bredFor = "Small rodent hunting, lapdog",
            image = "https://cdn2.thedogapi.com/images/BJa4kxc4X.jpg",
            description = null,
        ),
        DogFullEntity(
            id = "2",
            name = "Afghan Hound",
            origin = "Afghanistan, Iran, Pakistan",
            temperament = "Aloof, Clownish, Dignified, Independent, Happy",
            lifeSpan = "10 - 13 years",
            breedGroup = "Hound",
            bredFor = "Coursing and hunting",
            image = "https://cdn2.thedogapi.com/images/hMyT4CDXR.jpg",
            description = "",
        ),
        DogFullEntity(
            id = "3",
            name = "African Hunting Dog",
            origin = null,
            temperament = "Wild, Hardworking, Dutiful",
            lifeSpan = "11 years",
            breedGroup = null,
            bredFor = "A wild pack animal",
            image = "https://cdn2.thedogapi.com/images/rkiByec47.jpg",
            description = null,
        ),
        DogFullEntity(
            id = "4",
            name = "Airedale Terrier",
            origin = "United Kingdom, England",
            temperament = "Outgoing, Friendly, Alert, Confident, Intelligent, Courageous",
            lifeSpan = "10 - 13 years",
            breedGroup = "Terrier",
            bredFor = "Badger, otter hunting",
            image = "https://cdn2.thedogapi.com/images/1-7cgoZSh.jpg",
            description = null,
        ),
        DogFullEntity(
            id = "5",
            name = "Akbash Dog",
            origin = null,
            temperament = "Loyal, Independent, Intelligent, Brave",
            lifeSpan = "10 - 12 years",
            breedGroup = "Working",
            bredFor = "Sheep guarding",
            image = "https://cdn2.thedogapi.com/images/26pHT3Qk7.jpg",
            description = null,
            ),

        DogFullEntity(
            id = "6",
            name = "Akita",
            origin = null,
            temperament = "Docile, Alert, Responsive, Dignified, Composed, Friendly, Receptive, Faithful, Courageous",
            lifeSpan = "10 - 14 years",
            breedGroup = "Working",
            bredFor = "Hunting bears",
            image = "https://cdn2.thedogapi.com/images/BFRYBufpm.jpg",
            description = null,
        ),

        DogFullEntity(
            id = "7",
            name = "Alapaha Blue Blood Bulldog",
            origin = null,
            temperament = "Loving, Protective, Trainable, Dutiful, Responsible",
            lifeSpan = "12 - 13 years",
            breedGroup = "Mixed",
            bredFor = "Guarding",
            image = "https://cdn2.thedogapi.com/images/33mJ-V3RX.jpg",
            description = "The Alapaha Blue Blood Bulldog is a well-developed, exaggerated bulldog with a broad head and natural drop ears. The prominent muzzle is covered by loose upper lips. The prominent eyes are set well apart. The Alapaha's coat is relatively short and fairly stiff. Preferred colors are blue merle, brown merle, or red merle all trimmed in white or chocolate and white. Also preferred are the glass eyes (blue) or marble eyes (brown and blue mixed in a single eye). The ears and tail are never trimmed or docked. The body is sturdy and very muscular. The well-muscled hips are narrower than the chest. The straight back is as long as the dog is high at the shoulders. The dewclaws are never removed and the feet are cat-like."
        ),

        DogFullEntity(
            id = "8",
            name = "Alaskan Husky",
            origin = null,
            temperament = "Friendly, Energetic, Loyal, Gentle, Confident",
            lifeSpan = "10 - 13 years",
            breedGroup = "Mixed",
            bredFor = "Sled pulling",
            image = "https://cdn2.thedogapi.com/images/-HgpNnGXl.jpg",
            description = null,
        ),

    )
}
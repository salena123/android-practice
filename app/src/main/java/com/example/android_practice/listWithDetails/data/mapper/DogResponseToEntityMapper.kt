import com.example.android_practice.listWithDetails.data.model.DogBreed
import com.example.android_practice.listWithDetails.data.model.DogBreedListResponse
import com.example.android_practice.listWithDetails.data.model.DogFullListResponse
import com.example.android_practice.listWithDetails.domain.entity.DogFullEntity
import com.example.android_practice.listWithDetails.domain.entity.DogImage
import com.example.android_practice.listWithDetails.domain.entity.DogShortEntity


class DogResponseToEntityMapper {

    fun mapBreedList(response: List<DogBreed>) =
        response.map { breed ->
            DogShortEntity(
                id = breed.id.orEmpty(),
                name = breed.name.orEmpty(),
                temperament = breed.temperament.orEmpty(),
                image = breed.image?.url.orEmpty()
            )
        }

    fun mapSearchBreeds(response: List<DogBreed>) =
        response.map { breed ->
            DogShortEntity(
                id = breed.id.orEmpty(),
                name = breed.name.orEmpty(),
                temperament = breed.temperament.orEmpty(),
                image = breed.image?.url.orEmpty()
            )
        }


    fun mapBreedFull(response: List<DogFullListResponse>) =
        response.map {
            DogFullEntity(
                id = it.id.orEmpty(),
                name = it.name.orEmpty(),
                origin = it.origin.orEmpty(),
                temperament = it.temperament.orEmpty(),
                lifeSpan = it.lifeSpan.orEmpty(),
                breedGroup = it.breedGroup.orEmpty(),
                bredFor = it.bredFor.orEmpty(),
                image = DogImage(it.image?.url.orEmpty()),
                description = it.description.orEmpty(),
            )
        }


}


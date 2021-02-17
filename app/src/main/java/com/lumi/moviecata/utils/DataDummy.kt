package com.lumi.moviecata.utils

import com.lumi.moviecata.R
import com.lumi.moviecata.data.source.local.entity.MovieEntity
import com.lumi.moviecata.data.source.local.entity.SeriesEntity

object DataDummy {
    fun generateDummyMovies(): ArrayList<MovieEntity> {

        val movies = ArrayList<MovieEntity>()

        movies.add(
                MovieEntity("m1",
                        "Gabriel's Inferno Part III",
                        "The final part of the film adaption of the erotic romance novel Gabriel's Inferno written by an anonymous Canadian author under the pen name Sylvain Reynard.",
                        R.drawable.movie1)
        )
        movies.add(
                MovieEntity("m2",
                        "Gabriel's Inferno Part II",
                        "Professor Gabriel Emerson finally learns the truth about Julia Mitchell's identity, but his realization comes a moment too late. Julia is done waiting for the well-respected Dante specialist to remember her and wants nothing more to do with him. Can Gabriel win back her heart before she finds love in another's arms?",
                        R.drawable.movie2)
        )
        movies.add(
                MovieEntity("m3",
                        "Dilwale Dulhania Le Jayenge",
                        "Raj is a rich, carefree, happy-go-lucky second generation NRI. Simran is the daughter of Chaudhary Baldev Singh, who in spite of being an NRI is very strict about adherence to Indian values. Simran has left for India to be married to her childhood fiancé. Raj leaves for India with a mission at his hands, to claim his lady love under the noses of her whole family. Thus begins a saga.",
                        R.drawable.movie3)
        )
        movies.add(
                MovieEntity("m4",
                        "Gabriel's Inferno",
                        "An intriguing and sinful exploration of seduction, forbidden love, and redemption, Gabriel's Inferno is a captivating and wildly passionate tale of one man's escape from his own personal hell as he tries to earn the impossible--forgiveness and love.",
                        R.drawable.movie4)
        )
        movies.add(MovieEntity("m5",
                "The Shawshank Redemption",
                "Framed in the 1940s for the double murder of his wife and her lover, upstanding banker Andy Dufresne begins a new life at the Shawshank prison, where he puts his accounting skills to work for an amoral warden. During his long stretch in prison, Dufresne comes to be admired by the other inmates -- including an older prisoner named Red -- for his integrity and unquenchable sense of hope.",
                R.drawable.movie5)
        )
        movies.add(
                MovieEntity("m6",
                        "Wolfwalkers",
                        "In a time of superstition and magic, when wolves are seen as demonic and nature an evil to be tamed, a young apprentice hunter comes to Ireland with her father to wipe out the last pack. But when she saves a wild native girl, their friendship leads her to discover the world of the Wolfwalkers and transform her into the very thing her father is tasked to destroy.",
                        R.drawable.movie6)
        )
        movies.add(
                MovieEntity("m7",
                        "The Godfather",
                        "Spanning the years 1945 to 1955, a chronicle of the fictional Italian-American Corleone crime family. When organized crime family patriarch, Vito Corleone barely survives an attempt on his life, his youngest son, Michael steps in to take care of the would-be killers, launching a campaign of bloody revenge.",
                        R.drawable.movie7)
        )
        movies.add(
                MovieEntity("m8",
                        "Schindler's List",
                        "The true story of how businessman Oskar Schindler saved over a thousand Jewish lives from the Nazis while they worked as slaves in his factory during World War II.",
                        R.drawable.movie8)
        )
        movies.add(
                MovieEntity("m9",
                        "Your Name.",
                        "High schoolers Mitsuha and Taki are complete strangers living separate lives. But one night, they suddenly switch places. Mitsuha wakes up in Taki’s body, and he in hers. This bizarre occurrence continues to happen randomly, and the two must adjust their lives around each other.",
                        R.drawable.movie9)
        )
        movies.add(MovieEntity("m10",
                "The Godfather: Part II",
                "In the continuing saga of the Corleone crime family, a young Vito Corleone grows up in Sicily and in 1910s New York. In the 1950s, Michael Corleone attempts to expand the family business into Las Vegas, Hollywood and Cuba.",
                R.drawable.movie10))
        movies.add(
                MovieEntity("m11",
                        "Dedicated to my ex",
                        "The film tells the story of Ariel, a 21-year-old who decides to form a rock band to compete for a prize of ten thousand dollars in a musical band contest, this as a last option when trying to get money to save their relationship and reunite with his ex-girlfriend, which breaks due to the trip she must make to Finland for an internship. Ariel with her friend Ortega, decides to make a casting to find the other members of the band, although they do not know nothing about music, thus forming a band with members that have diverse and opposite personalities.",
                        R.drawable.movie1)
        )
        movies.add(
                MovieEntity("m12",
                        "Rascal Does Not Dream of a Dreaming Girl",
                        "In Fujisawa, Sakuta Azusagawa is in his second year of high school. Blissful days with his girlfriend and upperclassman, Mai Sakurajima, are interrupted by the appearance of his first crush, Shoko Makinohara.",
                        R.drawable.movie2)
        )
        movies.add(
                MovieEntity("m13",
                        "Miraculous World: New York, United HeroeZ",
                        "During a school field trip, Ladybug and Cat Noir meet the American superheroes, whom they have to save from an akumatised super-villain. They discover that Miraculous exist in the United States too.",
                        R.drawable.movie3)
        )
        movies.add(
                MovieEntity("m14",
                        "Spirited Away",
                        "A young girl, Chihiro, becomes trapped in a strange new world of spirits. When her parents undergo a mysterious transformation, she must call upon the courage she never knew she had to free her family.",
                        R.drawable.movie4)
        )
        movies.add(MovieEntity("m15",
                "Parasite",
                "All unemployed, Ki-taek's family takes peculiar interest in the wealthy and glamorous Parks for their livelihood until they get entangled in an unexpected incident.",
                R.drawable.movie5)
        )
        movies.add(
                MovieEntity("m16",
                        "Clouds",
                        "Young musician Zach Sobiech discovers his cancer has spread, leaving him just a few months to live. With limited time, he follows his dream and makes an album, unaware that it will soon be a viral music phenomenon.",
                        R.drawable.movie6)
        )
        movies.add(
                MovieEntity("m17",
                        "The Green Mile",
                        "A supernatural tale set on death row in a Southern prison, where gentle giant John Coffey possesses the mysterious power to heal people's ailments. When the cell block's head guard, Paul Edgecomb, recognizes Coffey's miraculous gift, he tries desperately to help stave off the condemned man's execution.",
                        R.drawable.movie7)
        )
        movies.add(
                MovieEntity("m18",
                        "Pulp Fiction",
                        "A burger-loving hit man, his philosophical partner, a drug-addled gangster's moll and a washed-up boxer converge in this sprawling, comedic crime caper. Their adventures unfurl in three stories that ingeniously trip back and forth in time.",
                        R.drawable.movie8)
        )
        movies.add(
                MovieEntity("m19",
                        "KonoSuba: God's Blessing on this Wonderful World! Legend of Crimson",
                        "It is not strange that the Demon Lord's forces fear the Crimson Demons, the clan from which Megumin and Yunyun originate. Even if the Demon Lord's generals attack their village, the Crimson Demons can just easily brush them off with their supreme mastery of advanced and overpowered magic.  When Yunyun receives a seemingly serious letter regarding a potential disaster coming to her hometown, she immediately informs Kazuma Satou and the rest of his party. After a series of wacky misunderstandings, it turns out to be a mere prank by her fellow demon who wants to be an author. Even so, Megumin becomes worried about her family and sets out toward the Crimson Demons' village with the gang.  There, Kazuma and the others decide to sightsee the wonders of Megumin's birthplace. However, they soon come to realize that the nonsense threat they received might have been more than just a joke.",
                        R.drawable.movie9)
        )
        movies.add(MovieEntity("m20",
                "Life Is Beautiful",
                "A touching story of an Italian book seller of Jewish ancestry who lives in his own little fairy tale. His creative and happy life would come to an abrupt halt when his entire family is deported to a concentration camp during World War II. While locked up he tries to convince his son that the whole thing is just a game.",
                R.drawable.movie10))

        return movies
    }

    fun generateDummySeries(): ArrayList<SeriesEntity> {

        val series = ArrayList<SeriesEntity>()

        series.add(
                SeriesEntity("s1",
                        "I Am Not an Animal",
                        "I Am Not An Animal is an animated comedy series about the only six talking animals in the world, whose cosseted existence in a vivisection unit is turned upside down when they are liberated by animal rights activists.",
                        R.drawable.serie1)
        )
        series.add(
                SeriesEntity("s2",
                        "The Promised Neverland",
                        "Surrounded by a forest and a gated entrance, the Grace Field House is inhabited by orphans happily living together as one big family, looked after by their \\\"Mama,\\\" Isabella. Although they are required to take tests daily, the children are free to spend their time as they see fit, usually playing outside, as long as they do not venture too far from the orphanage — a rule they are expected to follow no matter what. However, all good times must come to an end, as every few months, a child is adopted and sent to live with their new family... never to be heard from again.\\n\\nHowever, the three oldest siblings have their suspicions about what is actually happening at the orphanage, and they are about to discover the cruel fate that awaits the children living at Grace Field, including the twisted nature of their beloved Mama.",
                        R.drawable.serie2)
        )
        series.add(
                SeriesEntity("s3",
                        "Given",
                        "Tightly clutching his Gibson guitar, Mafuyu Satou steps out of his dark apartment to begin another day of his high school life. While taking a nap in a quiet spot on the gymnasium staircase, he has a chance encounter with fellow student Ritsuka Uenoyama, who berates him for letting his guitar's strings rust and break. Noticing Uenoyama's knowledge of the instrument, Satou pleads for him to fix it and to teach him how to play. Uenoyama eventually agrees and invites him to sit in on a jam session with his two band mates: bassist Haruki Nakayama and drummer Akihiko Kaji.\\n\\nSatou's voice is strikingly beautiful, filling Uenoyama with the determination to make Satou the lead singer of the band. Though reticent at first, Satou takes the offer after an emotional meeting with an old friend. With the support of his new friends, Satou must not only learn how to play guitar, but also come to terms with the mysterious circumstances that led him to be its owner.",
                        R.drawable.serie3)
        )
        series.add(
                SeriesEntity("s4",
                        "TONIKAWA: Over the Moon for You",
                        "First comes marriage, then comes an earthbound love that is out of this world!\\n\\nFrom the day his parents named him, Nasa Yuzaki has been destined for outer space...or so he believes. But while trying to join the space program, he is thrown for a loop when an accident introduces him to Tsukasa, a mysterious girl with strange powers. Is she an alien, a moon goddess or something else? Since she insists on marrying him, Nasa has plenty of chances to find out!",
                        R.drawable.serie4)
        )
        series.add(SeriesEntity("s5",
                "The Rising of the Shield Hero",
                "Iwatani Naofumi was summoned into a parallel world along with 3 other people to become the world's Heroes. Each of the heroes respectively equipped with their own legendary equipment when summoned, Naofumi received the Legendary Shield as his weapon. Due to Naofumi's lack of charisma and experience he's labeled as the weakest, only to end up betrayed, falsely accused, and robbed by on the third day of adventure. Shunned by everyone from the king to peasants, Naofumi's thoughts were filled with nothing but vengeance and hatred. Thus, his destiny in a parallel World begins...",
                R.drawable.serie5)
        )
        series.add(
                SeriesEntity("s6",
                        "Redo of Healer",
                        "In a world of monsters, adventurers and magic, some of the most gifted healers are subjugated to brute force. Keyaru gains the ability to rewind time and turns the tables on those who’ve exploited him in this dark fantasy tale of vengeance and fury.",
                        R.drawable.serie6)
        )
        series.add(
                SeriesEntity("s7",
                        "Banana Fish",
                        "Nature made Ash Lynx beautiful; nurture made him a cold ruthless killer. A runaway brought up as the adopted heir and sex toy of \\\"Papa\\\" Dino Golzine, Ash, now at the rebellious age of seventeen, forsakes the kingdom held out by the devil who raised him. But the hideous secret that drove Ash's older brother mad in Vietnam has suddenly fallen into Papa's insatiably ambitious hands—and it's exactly the wrong time for Eiji Okamura, a pure-hearted young photographer from Japan, to make Ash Lynx's acquaintance...",
                        R.drawable.serie7)
        )
        series.add(
                SeriesEntity("s8",
                        "Rent-a-Girlfriend",
                        "In today’s Japan, \\\"rental\\\" services can deliver an afternoon with a \\\"friend,\\\" a \\\"parent,\\\" even a fake girlfriend! Kinoshita Kazuya is a 20-year-old failure of a college student. He managed to kiss his girlfriend once, but was dumped after a month.  Completely spiteful, Kazuya gets just desperate enough to give it a try. But he quickly discovers how complicated it can be to \\\"rent\\\" an emotional connection… and his new \\\"girlfriend,\\\" Mizuhara Chizuru, who’s trying to keep her side hustle secret, will panic when she finds out her real life and Kazuya’s are intertwined in surprising ways! A reckless rom-com filled with love and excitement is about to begin!",
                        R.drawable.serie8)
        )
        series.add(
                SeriesEntity("s9",
                        "ORESUKI: Are you the only one who loves me?",
                        "Kisaragi Amatsuyu is invited out alone by the cool beauty upperclassman Cosmos and his childhood friend Himawari. Expecting to hear their confessions, he triumphantly goes to meet each of them in turn. But Cosmos and Himawari both instead confess to Amatsuyu that they like his friend. Amatsuyu fights this lonely battle, but there is another girl who is looking at him. She is a gloomy girl with glasses and braids. Amatsuyu finds that he hates her, because she's always turning her sharp tongue only on him and finding enjoyment in his troubles. But it turns out that she's the only one who actually does like him.",
                        R.drawable.serie9)
        )
        series.add(SeriesEntity("s10",
                "Your Lie in April",
                "Kousei Arima was a genius pianist until his mother's sudden death took away his ability to play. Each day was dull for Kousei. But, then he meets a violinist named Kaori Miyazono who has an eccentric playing style. Can the heartfelt sounds of the girl's violin lead the boy to play the piano again?",
                R.drawable.serie10)
        )
        series.add(
                SeriesEntity("s11",
                        "Seton Academy: Join the Pack!",
                        "Seton Academy, a school full of animals where, thanks to population decline, there are fewer humans than any other creature. Mazama Jin, an animal hater and the only human male in his class, falls in love with Hino Hitomi, the only female human, the moment he lays eyes her. However he soon finds himself entangled with various other creatures after he reluctantly joins the 'pack' of Lanka the wolf, the only other member of her pack. After getting to know each other, the two decide to create a cooking club, and after a few bad-blooded misunderstandings, Ranka soon joins the club as well.\\n\\nThus begins the howl-some and howl-arious story of two normal humans; an adorable wolf; a cheerful koala; a sluggish, blonde sloth; and a feline with cattitude in their newfound club—in a story that teaches that friendship can be forged by creatures of different kinds.",
                        R.drawable.serie1)
        )
        series.add(
                SeriesEntity("s12",
                        "Attack on Titan: No Regrets",
                        "Many years before becoming the famed captain of the Survey Corps, a young Levi struggles to survive in the capital's garbage dump, the Underground. As the boss of his own criminal operation, Levi attempts to get by with meager earnings while aided by fellow criminals, Isabel Magnolia and Farlan Church. With little hope for the future, Levi accepts a deal from the anti-expedition faction leader Nicholas Lobov, who promises the trio citizenship aboveground if they are able to successfully assassinate Erwin Smith, a squad leader of the Survey Corps.\\n\\nAs Levi and Erwin cross paths, Erwin acknowledges Levi's agility and skill and gives him the option to either become part of the expedition team, or be turned over to the Military Police, to atone for his crimes. Now closer to the man they are tasked to kill, the group plans to complete their mission and save themselves from a grim demise in the dim recesses of their past home. However, they are about to learn that the surface world is not as liberating as they had thought and that sometimes, freedom can come at a heavy price.\\n\\nBased on the popular spin-off manga of the same name, Attack on Titan: No Regrets illustrates the encounter between two of Attack on Titan's pivotal characters, as well as the events of the 23rd expedition beyond the walls.",
                        R.drawable.serie2)
        )
        series.add(
                SeriesEntity("s13",
                        "Yuri!!! on Ice",
                        "Yūri Katsuki carried all of Japan's hopes on his shoulders to win at the Gran Prix Finale ice skating competition, but suffered a crushing defeat. He returns home to Kyushu and half feels like he wants to retire, and half feels like he wants to continue ice skating. Suddenly the five-time consecutive world championship ice skater Victor Nikiforov appears before him with Yuri Plisetsky, a young Russian figure skater who is already defeating his seniors. Victor and both Yuris take up the challenge on an unprecedented Gran Prix series.",
                        R.drawable.serie3)
        )
        series.add(
                SeriesEntity("s14",
                        "Golden Time",
                        "Banri Tada is a freshman at a Tokyo law school. After an accident, he suffers severe memory loss. Despite the incident, he befriends fellow freshman, Mitsuo Yanagisawa, which leads him to the beautiful, yet obsessive, Kouko Kaga.",
                        R.drawable.serie4)
        )
        series.add(SeriesEntity("s15",
                "My Hero Academia",
                "In a world where eighty percent of the population has some kind of super-powered Quirk, Izuku was unlucky enough to be born completely normal. But that won’t stop him from enrolling in a prestigious hero academy. Now, he’ll get his first taste of brutal rivalry from other schools as he braves the cutthroat, no-holds-barred provisional license exam.",
                R.drawable.serie5)
        )
        series.add(
                SeriesEntity("s16",
                        "Maid Sama!",
                        "Misaki Ayuzawa is the first female student council president at a once all-boys school turned co-ed. She rules the school with strict discipline demeanor. But she has a secret—she works at a maid cafe due to her families circumstances. One day the popular A-student and notorious heart breaker Takumi Usui finds out her secret and makes a deal with her to keep it hush from the school in exchange for spending some time with him.",
                        R.drawable.serie6)
        )
        series.add(
                SeriesEntity("s17",
                        "Weightlifting Fairy Kim Bok-Joo",
                        "A spunky female weightlifter and free-spirited male swimmer meet on campus, only to find out their pasts may be intertwined.",
                        R.drawable.serie7)
        )
        series.add(
                SeriesEntity("s18",
                        "Kaguya-sama: Love is War",
                        "Considered a genius due to having the highest grades in the country, Miyuki Shirogane leads the prestigious Shuchiin Academy's student council as its president, working alongside the beautiful and wealthy vice president Kaguya Shinomiya. The two are often regarded as the perfect couple by students despite them not being in any sort of romantic relationship.",
                        R.drawable.serie8)
        )
        series.add(
                SeriesEntity("s19",
                        "Demon Slayer: Kimetsu no Yaiba",
                        "It is the Taishō period in Japan. Tanjirō, a kindhearted boy who sells charcoal for a living, finds his family slaughtered by a demon. To make matters worse, his younger sister Nezuko, the sole survivor, has been transformed into a demon herself. Though devastated by this grim reality, Tanjirō resolves to become a “demon slayer” so that he can turn his sister back into a human, and kill the demon that massacred his family.",
                        R.drawable.serie9)
        )
        series.add(SeriesEntity("s20",
                "We Never Learn: BOKUBEN",
                "Nariyuki Yuiga is in his last and most painful year of high school. In order to gain the “special VIP recommendation” which would grant him a full scholarship to college, he must now tutor his classmates as they struggle to prepare for entrance exams.\\n\\nAmong his pupils are “the sleeping beauty of the literary forest,” Fumino Furuhashi, and “the Thumbelina supercomputer,” Rizu Ogata–two of the most beautiful super-geniuses at the school! While these two were thought to be academically flawless, it turns out that they’re completely clueless outside of their pet subjects…!?\\n\\nAs Nariyuki’s life is turned upside down by these quirky girls who just never learn, he must do everything he can to get them accepted into college! The stage is set for this romantic comedy featuring prodigies who never learn when it comes to studying and love!",
                R.drawable.serie10)
        )

        return series
    }
}
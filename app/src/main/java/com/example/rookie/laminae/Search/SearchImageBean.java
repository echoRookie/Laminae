package com.example.rookie.laminae.search;

import com.example.rookie.laminae.user.UserPins.UserPinsBean;

import java.util.List;

/**
 * Created by rookie on 2018/5/4.
 */
public class SearchImageBean {
    private QueryBean query;
    private int pin_count;
    private int board_count;
    private int people_count;
    private int gift_count;
    //private FacetsEntity facets;
    private int page;
    private Object category;
    private List<UserPinsBean.UserPinsItem> pins;

    public QueryBean getQuery() {
        return query;
    }

    public void setQuery(QueryBean query) {
        this.query = query;
    }

    public int getPin_count() {
        return pin_count;
    }

    public void setPin_count(int pin_count) {
        this.pin_count = pin_count;
    }

    public int getBoard_count() {
        return board_count;
    }

    public void setBoard_count(int board_count) {
        this.board_count = board_count;
    }

    public int getPeople_count() {
        return people_count;
    }

    public void setPeople_count(int people_count) {
        this.people_count = people_count;
    }

    public int getGift_count() {
        return gift_count;
    }

    public void setGift_count(int gift_count) {
        this.gift_count = gift_count;
    }

//    public FacetsEntity getFacets() {
//        return facets;
//    }
//
//    public void setFacets(FacetsEntity facets) {
//        this.facets = facets;
//    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public Object getCategory() {
        return category;
    }

    public void setCategory(Object category) {
        this.category = category;
    }

    public List<UserPinsBean.UserPinsItem> getPins() {
        return pins;
    }

    public void setPins(List<UserPinsBean.UserPinsItem> pins) {
        this.pins = pins;
    }

    //  搜索内容和类型
    public class QueryBean{
        private String text;
        private String type;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
//  各种分类里面包含的图片数量
    public class FacetsEntity{
        private int missing;
        private int total;
        private int other;
        /**
         * pets : 6450
         * funny : 2284
         * travel_places : 1809
         * other : 1764
         * photography : 1760
         * design : 1652
         * illustration : 1529
         * apparel : 956
         * web_app_icon : 923
         * home : 779
         * diy_crafts : 584
         * desire : 567
         * beauty : 469
         * industrial_design : 419
         * kids : 298
         * people : 238
         * anime : 232
         * film_music_books : 218
         * tips : 191
         * quotes : 173
         * food_drink : 128
         * wedding_events : 123
         * art : 96
         * modeling_hair : 74
         * games : 55
         * men : 50
         * architecture : 30
         * data_presentation : 23
         * education : 20
         * fitness : 13
         * geek : 5
         * sports : 4
         * digital : 4
         * cars_motorcycles : 3
         */

        private ResultsBean results;

        public int getMissing() {
            return missing;
        }

        public void setMissing(int missing) {
            this.missing = missing;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getOther() {
            return other;
        }

        public void setOther(int other) {
            this.other = other;
        }

        public ResultsBean getResults() {
            return results;
        }

        public void setResults(ResultsBean results) {
            this.results = results;
        }

        public  class ResultsBean {
            private int pets;
            private int funny;
            private int travel_places;
            private int other;
            private int photography;
            private int design;
            private int illustration;
            private int apparel;
            private int web_app_icon;
            private int home;
            private int diy_crafts;
            private int desire;
            private int beauty;
            private int industrial_design;
            private int kids;
            private int people;
            private int anime;
            private int film_music_books;
            private int tips;
            private int quotes;
            private int food_drink;
            private int wedding_events;
            private int art;
            private int modeling_hair;
            private int games;
            private int men;
            private int architecture;
            private int data_presentation;
            private int education;
            private int fitness;
            private int geek;
            private int sports;
            private int digital;
            private int cars_motorcycles;

            public int getPets() {
                return pets;
            }

            public void setPets(int pets) {
                this.pets = pets;
            }

            public int getFunny() {
                return funny;
            }

            public void setFunny(int funny) {
                this.funny = funny;
            }

            public int getTravel_places() {
                return travel_places;
            }

            public void setTravel_places(int travel_places) {
                this.travel_places = travel_places;
            }

            public int getOther() {
                return other;
            }

            public void setOther(int other) {
                this.other = other;
            }

            public int getPhotography() {
                return photography;
            }

            public void setPhotography(int photography) {
                this.photography = photography;
            }

            public int getDesign() {
                return design;
            }

            public void setDesign(int design) {
                this.design = design;
            }

            public int getIllustration() {
                return illustration;
            }

            public void setIllustration(int illustration) {
                this.illustration = illustration;
            }

            public int getApparel() {
                return apparel;
            }

            public void setApparel(int apparel) {
                this.apparel = apparel;
            }

            public int getWeb_app_icon() {
                return web_app_icon;
            }

            public void setWeb_app_icon(int web_app_icon) {
                this.web_app_icon = web_app_icon;
            }

            public int getHome() {
                return home;
            }

            public void setHome(int home) {
                this.home = home;
            }

            public int getDiy_crafts() {
                return diy_crafts;
            }

            public void setDiy_crafts(int diy_crafts) {
                this.diy_crafts = diy_crafts;
            }

            public int getDesire() {
                return desire;
            }

            public void setDesire(int desire) {
                this.desire = desire;
            }

            public int getBeauty() {
                return beauty;
            }

            public void setBeauty(int beauty) {
                this.beauty = beauty;
            }

            public int getIndustrial_design() {
                return industrial_design;
            }

            public void setIndustrial_design(int industrial_design) {
                this.industrial_design = industrial_design;
            }

            public int getKids() {
                return kids;
            }

            public void setKids(int kids) {
                this.kids = kids;
            }

            public int getPeople() {
                return people;
            }

            public void setPeople(int people) {
                this.people = people;
            }

            public int getAnime() {
                return anime;
            }

            public void setAnime(int anime) {
                this.anime = anime;
            }

            public int getFilm_music_books() {
                return film_music_books;
            }

            public void setFilm_music_books(int film_music_books) {
                this.film_music_books = film_music_books;
            }

            public int getTips() {
                return tips;
            }

            public void setTips(int tips) {
                this.tips = tips;
            }

            public int getQuotes() {
                return quotes;
            }

            public void setQuotes(int quotes) {
                this.quotes = quotes;
            }

            public int getFood_drink() {
                return food_drink;
            }

            public void setFood_drink(int food_drink) {
                this.food_drink = food_drink;
            }

            public int getWedding_events() {
                return wedding_events;
            }

            public void setWedding_events(int wedding_events) {
                this.wedding_events = wedding_events;
            }

            public int getArt() {
                return art;
            }

            public void setArt(int art) {
                this.art = art;
            }

            public int getModeling_hair() {
                return modeling_hair;
            }

            public void setModeling_hair(int modeling_hair) {
                this.modeling_hair = modeling_hair;
            }

            public int getGames() {
                return games;
            }

            public void setGames(int games) {
                this.games = games;
            }

            public int getMen() {
                return men;
            }

            public void setMen(int men) {
                this.men = men;
            }

            public int getArchitecture() {
                return architecture;
            }

            public void setArchitecture(int architecture) {
                this.architecture = architecture;
            }

            public int getData_presentation() {
                return data_presentation;
            }

            public void setData_presentation(int data_presentation) {
                this.data_presentation = data_presentation;
            }

            public int getEducation() {
                return education;
            }

            public void setEducation(int education) {
                this.education = education;
            }

            public int getFitness() {
                return fitness;
            }

            public void setFitness(int fitness) {
                this.fitness = fitness;
            }

            public int getGeek() {
                return geek;
            }

            public void setGeek(int geek) {
                this.geek = geek;
            }

            public int getSports() {
                return sports;
            }

            public void setSports(int sports) {
                this.sports = sports;
            }

            public int getDigital() {
                return digital;
            }

            public void setDigital(int digital) {
                this.digital = digital;
            }

            public int getCars_motorcycles() {
                return cars_motorcycles;
            }

            public void setCars_motorcycles(int cars_motorcycles) {
                this.cars_motorcycles = cars_motorcycles;
            }
        }

    }
}

����   2 � 4com/dacodes/censos/ui/view/adapters/ImageListAdapter  wLandroidx/recyclerview/widget/RecyclerView$Adapter<Lcom/dacodes/censos/ui/view/adapters/ImageListAdapter$ImageHolder;>; 1androidx/recyclerview/widget/RecyclerView$Adapter  clickCensoSubject &Lio/reactivex/subjects/PublishSubject; RLio/reactivex/subjects/PublishSubject<Lcom/dacodes/censos/data/models/ImageItem;>; clickCensoEvent Lio/reactivex/Observable; ELio/reactivex/Observable<Lcom/dacodes/censos/data/models/ImageItem;>; #Lorg/jetbrains/annotations/NotNull; getClickCensoEvent ()Lio/reactivex/Observable; G()Lio/reactivex/Observable<Lcom/dacodes/censos/data/models/ImageItem;>; 	 
	   this 6Lcom/dacodes/censos/ui/view/adapters/ImageListAdapter; onCreateViewHolder ](Landroid/view/ViewGroup;I)Lcom/dacodes/censos/ui/view/adapters/ImageListAdapter$ImageHolder; Q(Landroid/view/ViewGroup;I)Landroidx/recyclerview/widget/RecyclerView$ViewHolder;  
   4androidx/recyclerview/widget/RecyclerView$ViewHolder  parent  kotlin/jvm/internal/Intrinsics  checkParameterIsNotNull '(Ljava/lang/Object;Ljava/lang/String;)V   
  ! android/view/ViewGroup # 
getContext ()Landroid/content/Context; % &
 $ ' android/view/LayoutInflater ) from 8(Landroid/content/Context;)Landroid/view/LayoutInflater; + ,
 * - 7 $androidx/databinding/DataBindingUtil 0 inflate _(Landroid/view/LayoutInflater;ILandroid/view/ViewGroup;Z)Landroidx/databinding/ViewDataBinding; 2 3
 1 4 4com/dacodes/censos/databinding/ItemCensoImageBinding 6 @com/dacodes/censos/ui/view/adapters/ImageListAdapter$ImageHolder 8 binding : checkExpressionValueIsNotNull <  
  = <init> o(Lcom/dacodes/censos/ui/view/adapters/ImageListAdapter;Lcom/dacodes/censos/databinding/ItemCensoImageBinding;)V ? @
 9 A 6Lcom/dacodes/censos/databinding/ItemCensoImageBinding; layoutInflater Landroid/view/LayoutInflater; Landroid/view/ViewGroup; viewType I getItemCount ()I censos Ljava/util/List; K L	  M java/util/List O size Q J P R onBindViewHolder F(Lcom/dacodes/censos/ui/view/adapters/ImageListAdapter$ImageHolder;I)V :(Landroidx/recyclerview/widget/RecyclerView$ViewHolder;I)V T U
  W holder Y get (I)Ljava/lang/Object; [ \ P ] (com/dacodes/censos/data/models/ImageItem _ 
getBinding 8()Lcom/dacodes/censos/databinding/ItemCensoImageBinding; a b
 9 c setCenso -(Lcom/dacodes/censos/data/models/ImageItem;)V e f
 7 g censoTitleTextView Landroid/widget/TextView; i j	 7 k !holder.binding.censoTitleTextView m 'com/example/balance/utils/FontSingleton o 	Companion 3Lcom/example/balance/utils/FontSingleton$Companion; q r	 p s 1com/example/balance/utils/FontSingleton$Companion u getMonstserratBold ()Landroid/graphics/Typeface; w x
 v y android/widget/TextView { setTypeface (Landroid/graphics/Typeface;)V } ~
 |  ivCensoImage Landroid/widget/ImageView; � �	 7 � getImage ()Landroid/net/Uri; � �
 ` � android/widget/ImageView � setImageURI (Landroid/net/Uri;)V � �
 � � censo *Lcom/dacodes/censos/data/models/ImageItem; BLcom/dacodes/censos/ui/view/adapters/ImageListAdapter$ImageHolder; position 	setImages (Ljava/util/List;)V ?(Ljava/util/List<Lcom/dacodes/censos/data/models/ImageItem;>;)V 	movieList � clear ()V � � P � java/util/Collection � addAll (Ljava/util/Collection;)Z � � P � notifyDataSetChanged � �
  � <Ljava/util/List<Lcom/dacodes/censos/data/models/ImageItem;>; K ? �
  � $io/reactivex/subjects/PublishSubject � create (()Lio/reactivex/subjects/PublishSubject; � �
 � �  	  �  io/reactivex/Observable � Lkotlin/Metadata; mv       bv        k d1E��H



��
!







��




 
��20R0��0:B00¢J0HJ02
0R0��20HJ0R0��2020HJ0200R00X¢
��R00¢
��	
R2&
 *00 *
 *0000X¢
��¨ d2 3Landroidx/recyclerview/widget/RecyclerView$Adapter;   kotlin.jvm.PlatformType ImageHolder 	app_debug ImageListAdapter.kt 	Signature RuntimeInvisibleAnnotations Code LineNumberTable LocalVariableTable $RuntimeInvisibleParameterAnnotations InnerClasses 
SourceFile RuntimeVisibleAnnotations 1          �      	 
  �     �         K L  �    �      �   /     *� �    �        �            �     �            �   �     ,+� "+� (� .N-/+� 5� 7:� 9Y*Y;� >� B�    �           �   4    : C    D E    ,       ,  F    , G H  �        �   	      A    �   "     
*+� � �    �         I J  �   4     
*� N� S �    �        �       
      T U  �   �     A+Z� "*� N� ^ � `N+� d-� h+� d� lYn� >� t� z� �+� d� �-� �� ��    �          ! 2 " @ & �   *   - � �    A       A Y �    A � H  �   	      A T V  �   "     
*+� 9� X�    �         � �  �   b     "+�� "*� N� � *� N+� �� � W*� ��    �      )  *  + ! - �       "       " � L  �    � �         ? �  �   h     (+�� "*� �*+� N*� �� �**� �Y�� >� �� �    �             �       (       ( K L  �
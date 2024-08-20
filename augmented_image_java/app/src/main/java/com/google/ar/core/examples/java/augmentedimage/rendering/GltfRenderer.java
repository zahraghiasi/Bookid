package com.google.ar.core.examples.java.augmentedimage.rendering;

import android.content.Context;
import android.util.Log;

import com.google.android.filament.Engine;
import com.google.android.filament.Renderer;
import com.google.android.filament.Scene;
import com.google.android.filament.SwapChain;
import com.google.android.filament.View;
import com.google.android.filament.gltfio.AssetLoader;
import com.google.android.filament.gltfio.FilamentAsset;
import com.google.android.filament.gltfio.MaterialProvider;
import com.google.android.filament.gltfio.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class GltfRenderer {
    private Engine engine;
    private Scene scene;
    private Renderer renderer;
    private View filamentView;
    private SwapChain swapChain;
    private FilamentAsset filamentAsset;
    private AssetLoader assetLoader;
    private ResourceLoader resourceLoader;

    public GltfRenderer(Context context, Engine engine, Scene scene, View filamentView, SwapChain swapChain) {
        this.engine = engine;
        this.scene = scene;
        this.renderer = engine.createRenderer();
        this.filamentView = filamentView;
        this.swapChain = swapChain;
        MaterialProvider materialProvider = new MaterialProvider(engine); // 'true' enables the Ubershader-like behavior
        this.assetLoader = new AssetLoader(engine, materialProvider, null);
        this.resourceLoader = new ResourceLoader(engine);
    }

    public void loadGltfModel(Context context, String assetPath) {
        try {
            // Load the GLTF file into a ByteBuffer
            ByteBuffer buffer = loadAsset(context, assetPath);

            // Create a Filament asset from the ByteBuffer
            filamentAsset = assetLoader.createAssetFromJson(buffer);
            if (filamentAsset != null) {
                // Load resources and add entities to the scene
                resourceLoader.loadResources(filamentAsset);
                scene.addEntities(filamentAsset.getEntities());
            } else {
                Log.e("GltfRenderer", "Failed to load GLTF model.");
            }
        } catch (IOException e) {
            Log.e("GltfRenderer", "Error loading GLTF file", e);
        }
    }

    public void render(long frameTimeNanos) {
        if (renderer.beginFrame(swapChain, frameTimeNanos)) {
            renderer.render(filamentView);
            renderer.endFrame();
        }
    }

    private ByteBuffer loadAsset(Context context, String assetPath) throws IOException {
        try (InputStream inputStream = context.getAssets().open(assetPath);
             ReadableByteChannel channel = Channels.newChannel(inputStream)) {
            ByteBuffer buffer = ByteBuffer.allocateDirect(inputStream.available());
            channel.read(buffer);
            buffer.rewind();
            return buffer;
        }
    }
}

